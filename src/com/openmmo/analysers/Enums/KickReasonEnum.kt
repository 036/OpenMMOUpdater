package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class KickReasonEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("SERVER_SHUTDOWN") }}
        .and { it.methods.any { it.instructionsContainsString("TAMPERING") }} //KEK
}