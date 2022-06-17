package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class DiscordVersionEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("CANARY") }}
        .and { it.methods.any { it.instructionsContainsString("PTB") } }
}