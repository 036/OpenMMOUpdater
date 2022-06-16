package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class Account : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.constructors.any{ it.arguments.startsWith(Type.INT_TYPE, String::class.type, Type.INT_TYPE, Type.BYTE_TYPE)} }
        .and { it.constructors.any { it.arguments.size == 17 } }
}