package com.openmmo.analysers.Frames

import com.openmmo.analysers.Widgets.BaseWidget
import com.openmmo.mapper.*

@DependsOn(BaseWidget::class)
class BattleAbilityFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseWidget>()}
        .and { it.methods.any { it.instructionsContainsString("battle-ability-enemy") } }
        .and { it.methods.any { it.instructionsContainsString("battle-ability") } }
}