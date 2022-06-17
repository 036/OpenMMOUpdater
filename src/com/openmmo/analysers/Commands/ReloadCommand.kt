package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class ReloadCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString(">reload <ui|commands>") }}
}