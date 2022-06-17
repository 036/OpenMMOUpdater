package com.openmmo.analysers.Frames

import com.openmmo.mapper.*

class BattleAbilityFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Widget")}
        .and { it.methods.any { it.instructionsContainsString("battle-ability-enemy") } }
        .and { it.methods.any { it.instructionsContainsString("battle-ability") } }
}