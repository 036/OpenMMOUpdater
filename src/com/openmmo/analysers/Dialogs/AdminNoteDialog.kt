package com.openmmo.analysers.Dialogs

import com.openmmo.mapper.*

class AdminNoteDialog : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("DialogLayout") }
        .and { it.methods.any { it.instructionsContainsString("/table") } }
        .and { it.methods.any { it.instructionsContainsString("/adminframe-dialog") } }
}