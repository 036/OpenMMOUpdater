package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class LearningMove : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.instanceFields.size == 3 }
        .and { it.constructors.any { it.arguments.size == 3 } }
        .and { it.constructors.any { it.arguments.endsWith(Type.SHORT_TYPE)} }
        .and { it.instanceFields.count { it.type.equals(Type.SHORT_TYPE) } == 1 }
        .and { it.instanceFields.count { it.type.equals(Type.BYTE_TYPE) } == 1 }
        .and { it.instanceMethods.any { it.name == "equals" }}
}