package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class NPCSpriteDebuggerFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("ResizableFrame")}
        .and { it.instanceFields.count { it.type.className.contains("IntegerModel") } == 3 }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "Npc Sprite Debugger" } } }
}