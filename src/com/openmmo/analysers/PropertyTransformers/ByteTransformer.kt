package com.openmmo.analysers.PropertyTransformers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(IPropertyTransformer::class)
class ByteTransformer : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.instanceMethods.isNotEmpty() }
        .and { it.interfaces.any { it.baseType == type<IPropertyTransformer>() } }
        .and { it.instanceMethods.any { it.arguments.size == 2 &&
                it.returnType.className.contains("Object") &&
                it.arguments[0].baseType.className.contains("String") &&
                it.arguments[1].baseType.className.contains("Field") &&
                it.invokesMethod(Opcodes.INVOKESTATIC, "decode", "java/lang/Byte") } }

    @DependsOn(ByteTransformer::class)
    class instance : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ByteTransformer>() }
    }

    class transform : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.size == 2 }
            .and { it.returnType.className.contains("Object") }
            .and { it.arguments[0].baseType.className.contains("String") }
            .and { it.arguments[1].baseType.className.contains("Field") }
    }
}