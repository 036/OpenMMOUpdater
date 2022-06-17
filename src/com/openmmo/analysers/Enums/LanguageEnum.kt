package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class LanguageEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("TR") }}
        .and { it.methods.any { it.instructionsContainsString("RU") } }
        .and { it.methods.any { it.instructionsContainsString("OTHER") } }
        .and { it.methods.any { it.instructionsContainsString("JA") } }

}