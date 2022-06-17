package com.openmmo.analysers

import com.openmmo.mapper.*

class PlatinumTownMapLoader : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("PlatinumTownMap has more data to be read ") }}
}