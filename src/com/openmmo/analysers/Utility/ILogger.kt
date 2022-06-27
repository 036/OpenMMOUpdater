package com.openmmo.analysers.Utility

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class ILogger : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.hasAccess(Opcodes.ACC_INTERFACE) }
        .and { it.methods.any { it.name == "isDebugEnabled" } }
        .and { it.methods.any { it.name == "getName" } }
        .and { it.methods.filter { it.name == "error" }.size == 3 }
}