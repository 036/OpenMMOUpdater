package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class PlayerEntity : IdentityMapper.Class() {
    override val predicate =
        predicateOf<ClassWrapper> { it.staticFields.any { it.type.className.contains("Comparator") } }
            .and { it.staticFields.size == 1 }
            .and { it.superType.size == 1}
            .and { it.interfaces.isEmpty() }
            .and { it.constructors.any { it.arguments.startsWith(Type.INT_TYPE) && it.arguments.endsWith(Type.BYTE_TYPE)}}

    @DependsOn(EntityPosition::class)
    class position() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<EntityPosition>() }
    }
}