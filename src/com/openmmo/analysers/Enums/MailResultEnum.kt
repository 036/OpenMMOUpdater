package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class MailResultEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("USER_NOT_FOUND") }}
        .and { it.methods.any { it.instructionsContainsString("SUBJECT_LENGTH") } }
}