package com.openmmo.analysers.Frames

import com.openmmo.analysers.Frames.AbstractResizableFrame
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class AdminPlayerSearchFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("ResizableFrame")}
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "adminframe-nontab" } } }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "Player Search" } } }
}