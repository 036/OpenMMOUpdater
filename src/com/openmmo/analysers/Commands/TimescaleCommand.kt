package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class TimescaleCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("You need to be in battle in order to use this command.") }}
        .and { it.methods.any { it.instructionsContainsString("Error parsing timescale check your request.") }}
}