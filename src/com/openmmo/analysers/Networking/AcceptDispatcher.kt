package com.openmmo.analysers.Networking

import com.openmmo.analysers.Utility.ILogger
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class AcceptDispatcher : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Accept Dispatcher") } }

    class acceptConnection : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.isEmpty() }
    }
}