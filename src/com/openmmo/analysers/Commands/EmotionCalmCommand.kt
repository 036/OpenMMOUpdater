package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class EmotionCalmCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("/em_calm") }}
}