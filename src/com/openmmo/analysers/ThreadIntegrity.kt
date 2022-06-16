package com.openmmo.analysers

import com.openmmo.mapper.*
import java.lang.reflect.Modifier

class ThreadIntegrity : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.staticFields.any { it.type.className.contains("Thread") } }
        .and { Modifier.isAbstract(it.access) }
        .and { it.interfaces.isEmpty() }
        .and { it.staticMethods.size == 2 }
}