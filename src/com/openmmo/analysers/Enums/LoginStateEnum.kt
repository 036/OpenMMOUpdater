package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class LoginStateEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("RATE_LIMITED") }}
        .and { it.methods.any { it.instructionsContainsString("WRONG_CODE_2FA") } }
}