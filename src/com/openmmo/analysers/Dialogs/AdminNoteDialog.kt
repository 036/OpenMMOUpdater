package com.openmmo.analysers.Dialogs

import com.openmmo.mapper.*

@DependsOn(BaseDialog::class)
class AdminNoteDialog : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseDialog>() }
        .and { it.methods.any { it.instructionsContainsString("/table") } }
        .and { it.methods.any { it.instructionsContainsString("/adminframe-dialog") } }
}