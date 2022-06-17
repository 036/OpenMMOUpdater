package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type
import java.lang.reflect.Modifier

@DependsOn(FooterPosition::class)
class ThreeDTile : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.size == 1 }
        .and { Modifier.isAbstract(it.access) }
        .and { it.constructors.any { it.arguments.size == 5 } }
        .and { it.interfaces.any { it.className.contains("java.lang.Cloneable") } }

    class movementPerms() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.SHORT_TYPE }
    }

    class type() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.BYTE_TYPE }
    }
}