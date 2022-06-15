package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class Evolution : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Widget")}
        .and { it.constructors.size == 1 }
        .and { it.constructors.all { ctor -> ctor.arguments.size == 1 } }
        .and { it.constructors.any { ctor -> ctor.arguments.startsWith(Type.BOOLEAN_TYPE) } }
        .and { it.fields.filter { field -> field.type == Type.BOOLEAN_TYPE }.size == 2 }
}