package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(BaseResizableFrame::class)
class MapDebuggerFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseResizableFrame>()}
        .and { it.instanceFields.any { it.type.className.contains("ColorAttribute") } }
        .and { it.instanceFields.any { it.type.className.contains("Material") } }
        .and { it.instanceFields.any { it.type.className.contains("TreeTable") } }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "/mapview-table" } } }
}