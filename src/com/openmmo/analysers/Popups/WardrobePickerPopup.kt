package com.openmmo.analysers.Popups

import com.openmmo.mapper.*

@DependsOn(BasePopup::class)
class WardrobePickerPopup : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BasePopup>()}
        .and { it.methods.any { it.instructionsMatchesString("wardrobe-picker") } }
}