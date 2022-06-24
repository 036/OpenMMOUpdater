package com.openmmo.analysers.Widgets

import com.openmmo.analysers.GameClass
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(GameClass::class, BaseWidget::class)
class CharacterSelectWidget : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseWidget>() }
        .and { it.constructors.any { it.arguments.contains(type<GameClass>()) } }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "characterselectgui" } } }
}