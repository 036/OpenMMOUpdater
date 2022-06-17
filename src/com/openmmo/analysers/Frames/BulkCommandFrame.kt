package com.openmmo.analysers.Frames

import com.openmmo.mapper.*

class BulkCommandFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Bulk Command") }}
        .and { it.methods.any { it.instructionsContainsString("adminframe") }}
        .and { it.methods.any { it.instructionsContainsString("\n") }}
}