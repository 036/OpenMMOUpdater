package com.openmmo.analysers

import com.openmmo.mapper.*

class ItemGameInfo : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.size == 2 }
        .and { it.interfaces.any { it.className.contains("Comparable") } }
        .and { it.interfaces.any { it.className.contains("Cloneable") } }
        .and { it.constructors.size == 2 }
        .and { it.staticFields.size == 3 }
}