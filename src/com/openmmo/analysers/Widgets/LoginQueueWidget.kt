package com.openmmo.analysers.Widgets

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class LoginQueueWidget : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Widget") }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "queuegui" +
                "" } } }
}