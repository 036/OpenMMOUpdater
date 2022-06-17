package com.openmmo.analysers.Dialogs

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class AdminPlayerLookupDialog : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("DialogLayout") }
        .and { it.fields.any { it.type.className.contains("Table") } }
        .and { it.constructors.all { it.arguments.isEmpty() } }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "Inspect Player" } } }
}