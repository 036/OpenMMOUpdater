package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class TwoDTile : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.constructors.any { it.arguments.size == 4 } }
        .and { it.constructors.any { it.arguments.startsWith(Type.SHORT_TYPE) } }
        .and { it.constructors.any { it.arguments.endsWith(Type.SHORT_TYPE) } }

    class movementPerms() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.SHORT_TYPE }
    }

    class type() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.BYTE_TYPE }
    }
}