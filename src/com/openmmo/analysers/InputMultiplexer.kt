package com.openmmo.analysers

import com.openmmo.mapper.*

class InputMultiplexer : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("processor cannot be null") }}
}