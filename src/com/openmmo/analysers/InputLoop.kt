package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class InputLoop : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.instanceMethods.any { it.name == "update" } }
        .and { it.instanceMethods.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "Error handling input from renderer." } } }
}