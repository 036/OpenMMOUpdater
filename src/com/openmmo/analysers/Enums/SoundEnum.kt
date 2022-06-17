package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class SoundEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("BACKGROUND") }}
        .and { it.methods.any { it.instructionsContainsString("FANFARE") } }
}