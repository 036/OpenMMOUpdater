package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import kotlin.and

class Settings : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "main.properties" } } }
}