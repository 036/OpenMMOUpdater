package com.openmmo.analysers

import com.openmmo.mapper.ClassWrapper
import com.openmmo.mapper.IdentityMapper
import com.openmmo.mapper.and
import com.openmmo.mapper.predicateOf
import org.objectweb.asm.Opcodes

class PCFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.size == 1 }
        .and { it.constructors.any { it.arguments.isEmpty() } }
        .and { it.fields.count { field -> field.type.className.contains("ArrayList") } == 1}
        .and { it.constructors.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "pc-frame" } } }
}