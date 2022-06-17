package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class GameEventTypeEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("HALLOWEEN") }}
        .and { it.methods.any { it.instructionsContainsString("CHRISTMAS") } }
}