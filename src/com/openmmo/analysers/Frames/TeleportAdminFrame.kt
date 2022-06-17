package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class TeleportAdminFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("ResizableFrame") }
        .and { it.constructors.any { it.arguments.size == 1 } }
        .and { it.constructors.any{ it.arguments.any { it.className.contains("Widget") }} }
        .and { it.methods.any { it.instructionsContainsString("adminframe") } }
        .and { it.methods.any { it.instructionsContainsString("Teleport") } }
}