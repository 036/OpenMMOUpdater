package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class AnimationTestCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("com.pokeemu.client.graphics.animation.battle.auto.special.Animation") } }
        .and { it.methods.any { it.instructionsContainsString("animtest") } }
}