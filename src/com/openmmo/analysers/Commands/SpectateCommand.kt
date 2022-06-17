package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class SpectateCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("/spectate <target name>") }}
}