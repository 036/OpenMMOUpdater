package com.openmmo.analysers.Table

import com.openmmo.mapper.*

class BaseTable : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsMatchesString("table") }}
        .and { it.constructors.size == 2 }
}