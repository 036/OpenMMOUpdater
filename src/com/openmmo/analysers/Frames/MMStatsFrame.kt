package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class MMStatsFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "mm-stats-window" } } }
        .and { it.superType.className.contains("ResizableFrame") }
        .and { it.instanceFields.any { it.type.className.contains("DialogLayout") } }
        .and { it.instanceFields.any { it.type.className.contains("TabbedPane") } }
        .and { it.staticFields.any { it.type.className.contains("SimpleDateFormat") } }
}