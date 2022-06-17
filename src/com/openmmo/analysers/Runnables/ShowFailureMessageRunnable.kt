package com.openmmo.analysers.Runnables

import com.openmmo.mapper.*

class ShowFailureMessageRunnable : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Runnable")}
        .and { it.methods.any { it.instructionsContainsString("Finished showing failure message") } }
}