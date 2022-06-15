package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class PokemonMove : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.constructors.size == 2 }
        .and { it.methods.any { it.name == "toString" }}
        .and { it.constructors.any { it.arguments.endsWith(Type.SHORT_TYPE) } }
}