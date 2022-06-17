package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class EncounterTypeEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("NATURE_BATTLE") }}
        .and { it.methods.any { it.instructionsContainsString("OW_LEGENDARY_BATTLE") } }
}