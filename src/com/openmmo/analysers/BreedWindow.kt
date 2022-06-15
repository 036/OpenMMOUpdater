package com.openmmo.analysers

import com.openmmo.mapper.ClassWrapper
import com.openmmo.mapper.IdentityMapper
import com.openmmo.mapper.and
import com.openmmo.mapper.predicateOf

class BreedWindow : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.fields.filter { it.desc.contains("Button") }.size == 2 }
        .and { it.fields.filter { it.desc.contains("Label") }.size == 6 }
        .and { it.interfaces.size == 1 }
}