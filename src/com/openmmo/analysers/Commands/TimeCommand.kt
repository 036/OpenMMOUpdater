package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class TimeCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("/time") }}
        .and { it.methods.any { it.instructionsContainsString("Current Time: ") } }
}