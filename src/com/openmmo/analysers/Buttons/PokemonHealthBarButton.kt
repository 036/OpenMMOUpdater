package com.openmmo.analysers.Buttons

import com.openmmo.mapper.*

@DependsOn(BaseButton::class)
class PokemonHealthBarButton : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseButton>()}
        .and { it.methods.any { it.instructionsContainsString("monsterframe-hp-progressbar") } }
}