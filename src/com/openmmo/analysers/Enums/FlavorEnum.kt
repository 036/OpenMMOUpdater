package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class FlavorEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("FLAVOR0") }}
}