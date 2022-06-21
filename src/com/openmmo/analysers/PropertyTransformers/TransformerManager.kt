package com.openmmo.analysers.PropertyTransformers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(IPropertyTransformer::class)
class TransformerManager : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.hasAccess(Opcodes.ACC_ABSTRACT) }
        .and { it.staticMethods.count() == 1 }
        .and { it.staticMethods.first().arguments.size == 2 }
        .and { it.staticMethods.first().returnType == type<IPropertyTransformer>() }
        .and { it.staticMethods.first().arguments[0].baseType.className.contains("Class") }
        .and { it.staticMethods.first().arguments[1].baseType.className.contains("Class") }

    @DependsOn(IPropertyTransformer::class)
    class GetTransformer : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.size == 2 }
            .and { it.returnType == type<IPropertyTransformer>() }
            .and { it.arguments[0].baseType.className.contains("Class") }
            .and { it.arguments[1].baseType.className.contains("Class") }
    }
}