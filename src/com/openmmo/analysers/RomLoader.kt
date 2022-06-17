package com.openmmo.analysers

import com.openmmo.mapper.*

class RomLoader : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.methods.any { it.instructionsContainsString("Possible rom ") } }
        .and { it.methods.any { it.instructionsContainsString("Could not load ") } }
}