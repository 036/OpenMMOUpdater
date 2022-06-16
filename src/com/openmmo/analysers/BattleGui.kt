package com.openmmo.analysers

import com.openmmo.mapper.*

class BattleGui : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Widget") }
        .and { it.fields.any { it.type.className.contains("LinkedList") } }
        .and { it.fields.any { it.type.className.contains("ArrayList") } }
        .and { it.fields.any { it.type.className.contains("Stack") } }
}