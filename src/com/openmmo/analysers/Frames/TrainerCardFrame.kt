package com.openmmo.analysers.Frames

import com.openmmo.mapper.*


class TrainerCardFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper>
    { it.methods.any { it.instructionsContainsString("trainercard") }}
}
