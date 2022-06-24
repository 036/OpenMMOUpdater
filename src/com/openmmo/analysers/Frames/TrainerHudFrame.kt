package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class TrainerHudFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("$0") } }
        .and { it.methods.any { it.instructionsContainsString("00:00") }}
}
