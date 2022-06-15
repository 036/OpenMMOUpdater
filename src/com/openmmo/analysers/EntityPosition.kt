package com.openmmo.analysers

import com.openmmo.Updater
import com.openmmo.mapper.*
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode

@DependsOn(FooterPosition::class)
class EntityPosition : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.instanceFields.any { field -> field.type == type<FooterPosition>() } }
        .and { it.constructors.first().arguments.size == 8 }
        .and { it.constructors.first().arguments.startsWith(Type.BYTE_TYPE) }

    class footerPosition : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<FooterPosition>() }
    }

    class setPosition: IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.size == 5 }
            .and { it.arguments.startsWith(Type.BOOLEAN_TYPE) }
            .and { it.arguments.endsWith(Type.BYTE_TYPE) }
    }

    class getEntityOnFooter : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType.equals(type<FooterPosition>()) }
            .and { it.arguments.isEmpty() }
    }
}