package com.openmmo.analysers.Networking

import com.openmmo.analysers.Utility.ILogger
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class ReadWriteDispatcher : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Unsupported readyOps ") } }

    @DependsOn(ILogger::class, ReadWriteDispatcher::class)
    class logger : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ILogger>() }
            .and { it.klass.type == type<ReadWriteDispatcher>() }
    }

    @DependsOn(BaseDataPacket::class)
    class addPacket : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 1 }
            .and { it.arguments[0].baseType == type<BaseDataPacket>() }
            .and { it.invokesMethod(Opcodes.INVOKEVIRTUAL, "add", "java/util/ArrayList") }
    }

    class checkStateAndSend : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.isEmpty() }
            .and { it.instructionsContainsString("Unsupported readyOps ") }
    }

    class queuedPackets : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.className.contains("ArrayList") }
    }
}