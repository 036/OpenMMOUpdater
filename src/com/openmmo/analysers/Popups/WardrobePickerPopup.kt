package com.openmmo.analysers.Popups

import com.openmmo.mapper.*

class WardrobePickerPopup : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("PopupWindow")}
        .and { it.methods.any { it.instructionsContainsString("wardrobe-picker") } }
}