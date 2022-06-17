package com.openmmo.analysers.Utility

import com.openmmo.mapper.*

class PointDataHandler : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString(" Not a valid pointdata file.") }}
}