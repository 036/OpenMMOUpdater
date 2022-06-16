package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import java.lang.reflect.Modifier

class Process : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access) }
        .and { it.interfaces.isEmpty() }
        .and { it.methods.any { it.instructions.filter { ins -> ins.opcode == Opcodes.INVOKESTATIC }.any { it.methodName == "allProcesses" } } }
}