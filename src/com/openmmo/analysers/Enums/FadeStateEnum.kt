package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class FadeStateEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("FADEIN_BLACK") }}
}