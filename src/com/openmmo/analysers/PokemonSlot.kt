package com.openmmo.analysers

import com.openmmo.mapper.*

class PokemonSlot : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.size == 1 }
        .and { it.constructors.any { it.instructionsContainsString("monster-slot") && it.instructionsContainsString("slot-health-progressbar") } }
}