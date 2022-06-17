package com.openmmo.analysers.Frames

import com.openmmo.mapper.*

class ContestResultFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("contestgui") }}
        .and { it.methods.any { it.instructionsContainsString("contestresulttext") }}
}