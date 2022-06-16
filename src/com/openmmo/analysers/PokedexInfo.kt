package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class PokedexInfo : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.constructors.any { it.arguments.startsWith(Type.SHORT_TYPE) } }
        .and { it.instanceFields.any { field -> field.desc.contains("HashSet") }}
        .and { it.instanceFields.any { field -> field.desc.contains("ArrayList") }}
}