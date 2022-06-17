package com.openmmo.analysers

import com.openmmo.mapper.*

class StringContainerPopulator : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Populating primary string container: ") }}
}