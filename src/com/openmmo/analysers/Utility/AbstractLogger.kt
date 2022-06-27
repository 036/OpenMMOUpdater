package com.openmmo.analysers.Utility

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class AbstractLogger : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen") }}

    class createLoggerFromClass : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == type<ILogger>() }
            .and { it.instructionsContainsString("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen") }
    }

}