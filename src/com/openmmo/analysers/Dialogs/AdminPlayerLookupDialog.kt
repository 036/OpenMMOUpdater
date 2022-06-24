package com.openmmo.analysers.Dialogs

import com.openmmo.analysers.Table.BaseTable
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(BaseDialog::class, BaseTable::class)
class AdminPlayerLookupDialog : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseDialog>() }
        .and { it.fields.any { it.type == type<BaseTable>() } }
        .and { it.constructors.all { it.arguments.isEmpty() } }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "Inspect Player" } } }
}