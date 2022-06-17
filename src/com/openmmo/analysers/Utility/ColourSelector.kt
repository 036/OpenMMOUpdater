package com.openmmo.analysers.Utility

import com.openmmo.mapper.*

class ColourSelector : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper>
    { it.methods.any { it.instructionsContainsString("Color parameter outside of expected range:") }}
}