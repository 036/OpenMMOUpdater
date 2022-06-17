package com.openmmo.analysers.Frames

import com.openmmo.mapper.*

class PrivateMessageFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.methods.any { it.instructionsContainsString("pm-box-frame") } }
        .and { it.methods.any { it.instructionsContainsString("PM (") } }
}