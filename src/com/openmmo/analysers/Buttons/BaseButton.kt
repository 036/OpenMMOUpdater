package com.openmmo.analysers.Buttons

import com.openmmo.mapper.*

class BaseButton : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsMatchesString("armed") }}
        .and { it.methods.any { it.instructionsMatchesString("button") } }
        .and { it.methods.any { it.instructionsMatchesString("pressed") } }
        .and { it.methods.any { it.instructionsMatchesString("selected") } }
}