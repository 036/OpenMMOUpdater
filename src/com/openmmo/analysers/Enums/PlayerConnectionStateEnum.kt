package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class PlayerConnectionStateEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("AUTHED") }}
        .and { it.methods.any { it.instructionsContainsString("DISCONNECTED") } }
}