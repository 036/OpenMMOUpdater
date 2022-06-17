package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class ModelTestCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("/modeltest") }}
}