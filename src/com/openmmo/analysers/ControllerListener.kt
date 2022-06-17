package com.openmmo.analysers

import com.openmmo.mapper.*

class ControllerListener : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("ControllerAdapter")}
        .and { it.methods.filter { it.name == "disconnected" }.any { it.instructionsContainsString("Controller ") } }
}