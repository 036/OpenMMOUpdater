package com.openmmo.analysers.PropertyTransformers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class IPropertyTransformer : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.hasAccess(Opcodes.ACC_INTERFACE) && it.hasAccess(Opcodes.ACC_ABSTRACT) }
        .and { it.instanceMethods.count() == 1 }
        .and { it.instanceMethods.first().arguments.size == 2 }
        .and { it.instanceMethods.first().returnType.className.contains("Object") }
        .and { it.instanceMethods.first().arguments[0].baseType.className.contains("String") }
        .and { it.instanceMethods.first().arguments[1].baseType.className.contains("Field") }

    class transform : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.size == 2 }
            .and { it.returnType.className.contains("Object") }
            .and { it.arguments[0].baseType.className.contains("String") }
            .and { it.arguments[1].baseType.className.contains("Field") }
    }
}