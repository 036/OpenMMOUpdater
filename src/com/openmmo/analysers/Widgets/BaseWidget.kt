package com.openmmo.analysers.Widgets

import com.openmmo.mapper.*

class BaseWidget : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsMatchesString("Exception in widgetDisabled()") }}
        .and { it.methods.any { it.instructionsMatchesString("child widget already in tree") } }
}