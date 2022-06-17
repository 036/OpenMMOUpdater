package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class ControllerDirectionEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("LEFTX") }}
        .and { it.methods.any { it.instructionsContainsString("RIGHTX") } }
}