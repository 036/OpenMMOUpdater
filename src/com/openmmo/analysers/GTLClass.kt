package com.openmmo.analysers

import com.openmmo.mapper.*
import kotlin.and

class GTLClass : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.size == 1 }
        .and { it.instanceFields.any { field -> field.desc.contains("SimpleIntegerModel") }}
        .and { it.instanceFields.any { field -> field.desc.contains("DialogLayout") }}
        .and { it.instanceFields.any { field -> field.desc.contains("EditField") }}
        .and { it.instanceFields.any { field -> field.desc.contains("Timer") }}


}