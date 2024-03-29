package com.openmmo.analysers.ProgressBar

import com.openmmo.mapper.*

@DependsOn(BaseProgressBar::class)
class LoveMeterProgressBar : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseProgressBar>()}
        .and { it.methods.any { it.instructionsContainsString("love-meter") } }
}