package com.openmmo.analysers

import com.openmmo.mapper.*

class PokemonInfo : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.constructors.any { it.arguments.size == 1 } }
        .and { it.instanceFields.any { it.type.className.contains("EnumMap") } }
}