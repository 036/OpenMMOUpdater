package com.openmmo.analysers.Frames

import com.openmmo.mapper.*

@DependsOn(BaseResizableFrame::class)
class GameCornerFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseResizableFrame>()}
        .and { it.methods.any { it.instructionsMatchesString("base-frame") } }
}