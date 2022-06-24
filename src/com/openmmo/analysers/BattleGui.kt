package com.openmmo.analysers

import com.openmmo.analysers.Widgets.BaseWidget
import com.openmmo.mapper.*

@DependsOn(BaseWidget::class)
class BattleGui : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseWidget>() }
        .and { it.fields.any { it.type.className.contains("LinkedList") } }
        .and { it.fields.any { it.type.className.contains("ArrayList") } }
        .and { it.fields.any { it.type.className.contains("Stack") } }
}