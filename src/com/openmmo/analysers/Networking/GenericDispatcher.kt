package com.openmmo.analysers.Networking

import com.openmmo.analysers.Utility.ILogger
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class GenericDispatcher : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Invalid packet size from client : ") } }
        .and { it.methods.any { it.instructionsContainsString(" packet size: ") } }

    @DependsOn(ILogger::class, GenericDispatcher::class)
    class logger : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ILogger>() }
            .and { it.klass.type == type<GenericDispatcher>() }
    }

    class sendData : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 1 }
            .and { it.arguments[0].baseType.className.contains("SelectionKey") }
            .and { it.instructionsContainsString("Write Error: ") }
    }

    class disconnectTask : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.instructionsContainsString("disconnectTask Error") }
    }

    class shouldRun : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.BOOLEAN_TYPE }
    }

    class refreshDelay : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type == Type.INT_TYPE }[0] == it }
    }

    class disconnectTaksCooldown : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.LONG_TYPE }
    }

    class lock : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.className.contains("Object") }
    }

    class checkStateAndSend : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.isEmpty() }
            .and { it.hasAccess(Opcodes.ACC_ABSTRACT) }
    }

    class addPacket : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 1 }
            .and { it.arguments[0].baseType == type<BaseDataPacket>() }
            .and { it.hasAccess(Opcodes.ACC_ABSTRACT) }
    }
}