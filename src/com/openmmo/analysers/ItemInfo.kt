package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class ItemInfo : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.constructors.size == 1 }
        .and { it.constructors.first().arguments.size == 6 }
        .and { it.instanceMethods.size == 3 }
        .and { it.instanceMethods.count { it.returnType == Type.SHORT_TYPE } == 2 }
        .and { it.instanceMethods.count { it.returnType == Type.BYTE_TYPE } == 1 }

}