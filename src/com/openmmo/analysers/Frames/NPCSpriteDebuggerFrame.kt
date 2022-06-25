package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(BaseResizableFrame::class)
class NPCSpriteDebuggerFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseResizableFrame>()}
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "Npc Sprite Debugger" } } }
        .and { it.methods.any { it.instructionsMatchesString("HGSS Mapped Sprite: ") } }
}