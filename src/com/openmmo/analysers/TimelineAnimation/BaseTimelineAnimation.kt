package com.openmmo.analysers.TimelineAnimation

import com.openmmo.mapper.*

class BaseTimelineAnimation : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Unknown target_id for getScaleAnimation = ") }}

}