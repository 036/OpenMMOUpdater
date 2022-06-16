package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class Item : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.size == 1 }
        .and { it.interfaces.any { it.className.contains("Comparable") } }
        .and { it.constructors.any { it.arguments.size == 1 }}
        .and { it.instanceFields.size == 2 }
        .and { it.instanceMethods.count { it.returnType == Type.SHORT_TYPE } == 2 }
}