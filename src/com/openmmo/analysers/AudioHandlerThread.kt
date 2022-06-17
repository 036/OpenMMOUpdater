package com.openmmo.analysers

import com.openmmo.mapper.*

class AudioHandlerThread : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.any { it.className.contains("Disposable") }}
        .and { it.superType.className.contains("Thread") }
        .and { it.methods.any { it.instructionsContainsString("sseqj") } }
}