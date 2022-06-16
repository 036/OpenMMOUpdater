package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class Captcha : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.fields.any { field -> field.type.className.contains("ByteArrayOutputStream") } }
        .and { it.interfaces.isEmpty() }
        .and { it.methods.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "Error reading image" } } }
}