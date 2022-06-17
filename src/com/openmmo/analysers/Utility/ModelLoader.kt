package com.openmmo.analysers.Utility

import com.openmmo.mapper.*

class ModelLoader : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("data/sprites/models/") }}
        .and { it.methods.any { it.instructionsContainsString("data/sprites/models.pak") }}
        .and { it.methods.any { it.instructionsContainsString("Error loading model data.") }}
}