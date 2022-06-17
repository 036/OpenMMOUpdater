package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class DebugCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString(">debug <ui|buildmode>") }}
}