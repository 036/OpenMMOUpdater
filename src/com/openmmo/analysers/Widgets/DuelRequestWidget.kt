package com.openmmo.analysers.Widgets

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(BaseWidget::class)
class DuelRequestWidget : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseWidget>()}
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "duel-confirm-widget" } } }
}