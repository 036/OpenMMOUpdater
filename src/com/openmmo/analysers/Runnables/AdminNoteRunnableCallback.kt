package com.openmmo.analysers.Runnables

import com.openmmo.analysers.Dialogs.AdminNoteDialog
import com.openmmo.mapper.*

@DependsOn(AdminNoteDialog::class)
class AdminNoteRunnableCallback : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.any { it.className.contains("Runnable") }}
        .and { it.instanceFields.any { it.type == type<AdminNoteDialog>() } }
        .and { it.methods.any { it.instructionsContainsString("") } }
}