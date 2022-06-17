package com.openmmo.analysers.Utility

import com.openmmo.mapper.*

class AbstractLogger : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen") }}
}