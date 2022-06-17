
package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class SetConfigCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> {it.methods.any { it.instructionsContainsString(">setconfig <CONFIG_NAME> [value]") }}
}