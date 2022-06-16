package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class PokemonBase : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.any { it.className.contains("Cloneable") } }
        .and { it.constructors.any { it.arguments.startsWith(Type.INT_TYPE) } }
        .and { it.instanceFields.count { it.type == Type.BYTE_TYPE } > 8 }
}