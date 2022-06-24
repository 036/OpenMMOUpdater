package com.openmmo.analysers.ProgressBar

import com.openmmo.mapper.*

class PokemonHealthProgressBar : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("ProgressBar")}
        .and { it.methods.any { it.name.contains("handleEvent") } }
        .and { it.methods.any { it.name.contains("getMinHeight") } }
}