package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class TranslationHelper : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("\\|[^\\|]+\\|") }}

    class GetStringByID() : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType.className.contains("String") }
            .and { it.arguments.size == 1 }
            .and { it.arguments[0].baseType == Type.INT_TYPE }
            .and { it.instructionsContainsString("STRING_") }
    }
}