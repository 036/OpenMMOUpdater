package com.openmmo.analysers.Runnables

import com.openmmo.mapper.*

class RomDecryptionRunnable : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Error loading cryptographic service.") }}
}