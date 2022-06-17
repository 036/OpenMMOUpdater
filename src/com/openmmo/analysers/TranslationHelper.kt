package com.openmmo.analysers

import com.openmmo.mapper.*

class TranslationHelper : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("\\|[^\\|]+\\|") }}
}