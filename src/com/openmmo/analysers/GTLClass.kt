package com.openmmo.analysers

import com.openmmo.mapper.*
import kotlin.and

@DependsOn(PokemonClass::class)
class GTLClass : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsMatchesString("mobile-") } }
        .and { it.methods.any { it.instructionsMatchesString("broker-window") } }
}