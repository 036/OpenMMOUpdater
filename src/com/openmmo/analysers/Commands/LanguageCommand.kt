package com.openmmo.analysers.Commands

import com.openmmo.mapper.*

class LanguageCommand : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("/lang <language code>") }}
}