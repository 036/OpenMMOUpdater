package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class EncounterLocationEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("GRASS_DARK") }}
        .and { it.methods.any { it.instructionsContainsString("UNDERWATER") } }
        .and { it.methods.any { it.instructionsContainsString("CAVE_DARK") } }
}