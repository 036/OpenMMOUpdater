package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class StringBuilderHelper : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.size == 1 }
        .and { it.staticMethods.filter { m -> m.arguments.size == 3  && m.arguments[0].baseType.internalName.contains("StringBuilder") && m.arguments[1].baseType.internalName.contains("String") && m.arguments[2].baseType.internalName.contains("String")}.size == 1 }

    @DependsOn(StringBuilderHelper::class)
    class combineToStringBuilder : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.klass.type == type<StringBuilderHelper>() }
    }
}