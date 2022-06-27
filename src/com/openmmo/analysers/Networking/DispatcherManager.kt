package com.openmmo.analysers.Networking

import com.openmmo.analysers.Utility.ILogger
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class DispatcherManager : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("NIO Initialization Error: ") } }

    @DependsOn(ILogger::class, DispatcherManager::class)
    class logger : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ILogger>() }
            .and { it.klass.type == type<DispatcherManager>() }
    }

    class startReadWriteDispatcher : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 2 }
            .and { it.instructionsContainsString("AcceptReadWrite Dispatcher") }
            .and { it.instructionsContainsString("ReadWrite-") }
    }

    class startNio : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.isEmpty() }
            .and { it.instructionsContainsString("NIO Initialization Error: ") }
    }

    @DependsOn(GenericDispatcher::class)
    class getNextDispatcher : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == type<GenericDispatcher>() }
            .and { it.arguments.isEmpty() }
    }

    @DependsOn(GenericDispatcher::class)
    class acceptDispatcher : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<GenericDispatcher>() }
    }

    class nioStarted : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.BOOLEAN_TYPE }
    }

    @DependsOn(GenericDispatcher::class)
    class readWriteDispatchers : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type.baseType == type<GenericDispatcher>() }[1] == it }
    }
}