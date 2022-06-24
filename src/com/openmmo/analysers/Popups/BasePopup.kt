package com.openmmo.analysers.Popups

import com.openmmo.mapper.*

class BasePopup : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsMatchesString("popupwindow") }}
}