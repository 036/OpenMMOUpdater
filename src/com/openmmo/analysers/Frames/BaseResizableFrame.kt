package com.openmmo.analysers.Frames

import com.openmmo.mapper.*

class BaseResizableFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsMatchesString("resizableframe") }}
        .and { it.methods.any { it.instructionsContainsString("fade") }  }
        .and { it.methods.any { it.instructionsContainsString("closeButton") } }
}