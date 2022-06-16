package com.openmmo.analysers

import com.openmmo.mapper.*

class MoveButton : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Button") }
        .and { it.instanceFields.any { it.type.className.contains("CharSequence") } }
        .and { it.instanceFields.any { it.type.className.contains("BitmapFontCache") } }
        .and { it.instanceFields.any { it.type.className.contains("Alignment") } }
        .and { it.instanceFields.any { it.type.className.contains("Font") } }
}