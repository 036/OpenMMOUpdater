package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class FightingPokemon : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.methods.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "#F93822" } } }
        .and { it.methods.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "#FFD033" } } }
}