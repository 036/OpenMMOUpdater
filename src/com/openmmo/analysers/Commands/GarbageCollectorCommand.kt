package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class GarbageCollectorCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("--- Garbage Collection Initiated ---") }}
}