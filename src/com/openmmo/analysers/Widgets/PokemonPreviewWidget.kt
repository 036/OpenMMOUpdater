package com.openmmo.analysers.Widgets

import com.openmmo.analysers.PokemonClass
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(PokemonClass::class, BaseWidget::class)
class PokemonPreviewWidget : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseWidget>() }
        .and { it.constructors.any { it.arguments.contains(type<PokemonClass>()) } }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "monster-preview-panel" } } }
}