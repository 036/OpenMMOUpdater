package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type
import java.lang.reflect.Modifier

class PokemonInventory : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access) }
        .and { it.constructors.any { it.arguments.size == 1 } }
        .and { it.instanceFields.any { field -> field.desc.contains("HashMap") }}
        .and { it.instanceFields.count { it.type == Type.BOOLEAN_TYPE } == 2 }
}