package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class CharmTypeEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("SHINY_CHARM") }}
        .and { it.methods.any { it.instructionsContainsString("EVENT_REPEL") } }
}