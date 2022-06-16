package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class FightingPokemonInner : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.instanceFields.size == 5 }
        .and { it.constructors.any { it.arguments.size == 3 } }
        .and { it.constructors.any{ it.arguments.endsWith(Type.BYTE_TYPE)} }
        .and { it.instanceMethods.size == 3 }
}