package com.openmmo.analysers.Enums

import com.openmmo.mapper.*

class WeatherEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("SHITTY_RAIN") }}
}