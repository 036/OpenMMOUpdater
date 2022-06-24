package com.openmmo.analysers.Widgets

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(BaseWidget::class)
class LoginQueueWidget : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("queuegui") }}
        .and { it.superType == type<BaseWidget>() }
}