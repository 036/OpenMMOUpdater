package com.openmmo.analysers

import com.openmmo.mapper.*

class AudioHandlerThread : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Thread") }
        .and { it.methods.any { it.instructionsContainsString("sseqj") } }
        .and { it.methods.any { it.instructionsContainsString("soundmuxer") } }
        .and { it.methods.any { it.instructionsContainsString("Unable to allocate nds sdat") } }
}