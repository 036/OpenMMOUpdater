package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class TeleportAdminFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.constructors.any { it.arguments.size == 1 } }
        .and { it.methods.any { it.instructionsContainsString("adminframe") } }
        .and { it.methods.any { it.instructionsContainsString("Teleport") } }
        .and { it.methods.any { it.instructionsContainsString("GM Menu") } }
}