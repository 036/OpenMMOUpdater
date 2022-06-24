package com.openmmo.analysers.ProgressBar

import com.openmmo.mapper.*

class BaseProgressBar : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsMatchesString("progressbar") }}
        .and { it.methods.any { it.instructionsMatchesString("indeterminate") }}
        .and { it.methods.any { it.instructionsMatchesString("valueChanged") }}
        .and { it.methods.any { it.instructionsMatchesString("progressImage") }}
}