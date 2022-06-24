package com.openmmo.analysers

import com.openmmo.analysers.Buttons.BaseButton
import com.openmmo.mapper.*

@DependsOn(BaseButton::class)
class BreedWindow : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.fields.filter { it.desc.contains(type<BaseButton>().descriptor) }.size == 2 }
        .and { it.fields.filter { it.desc.contains("Label") }.size == 6 }
        .and { it.interfaces.size == 1 }
}