package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class BattleDesyncDebugCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("battle desync debugs ") }}
}