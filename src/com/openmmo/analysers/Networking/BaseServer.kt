package com.openmmo.analysers.Networking

import com.openmmo.analysers.Utility.ILogger
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class BaseServer : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString(",age=") } }
        .and { it.methods.any { it.instructionsContainsString(",closed=") } }

    @DependsOn(ILogger::class, BaseServer::class)
    class logger : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ILogger>() }
            .and { it.klass.type == type<BaseServer>() }
    }

    class socketChannel : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.className.contains("SocketChannel") }
    }

    @DependsOn(GenericDispatcher::class)
    class dispatcher : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<GenericDispatcher>() }
    }

    class selectionKey : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.className.contains("SelectionKey") }
    }

    class finalDataBuffer : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type.className.contains("ByteBuffer") }[0] == it }
    }

    class lock : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.className.contains("Object") }
    }

    class timestamp : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.LONG_TYPE }
    }

    class addSelfToDispatcherQueue : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.isEmpty() }
            .and { !it.hasAccess(Opcodes.ACC_ABSTRACT) }
            .and { it.instructions.any { it.opcode == Opcodes.MONITORENTER } }
    }

}