package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class ControllerButtonsEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("RIGHTBUMPER") }}
        .and { it.methods.any { it.instructionsContainsString("BUTTON_PADDLE1") } }
}