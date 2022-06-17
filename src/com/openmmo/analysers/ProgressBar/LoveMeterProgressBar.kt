package com.openmmo.analysers.ProgressBar

import com.openmmo.mapper.*

class LoveMeterProgressBar : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("ProgressBar")}
        .and { it.methods.any { it.instructionsContainsString("love-meter") } }
}