package com.openmmo.analysers

import com.openmmo.mapper.*

class CancelEvolutionRunnable : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.any { it.className.contains("Runnable") } }
        .and { it.instanceFields.size == 1 }
        .and { it.constructors.any { it.arguments.size == 1 } }
}