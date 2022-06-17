package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class SetClientConfig : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString(">setclientconfig <CONFIG_NAME> [value]") }}
}