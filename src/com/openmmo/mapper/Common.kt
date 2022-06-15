package com.openmmo.mapper

import org.objectweb.asm.Opcodes

@DependsOn(Strings::class)
abstract class StringsUniqueMapper(string: String) : UniqueMapper.InClassInitializer.Field(Strings::class) {
    override val predicate = predicateOf<InstructionWrapper> { it.opcode == Opcodes.LDC && it.ldcCst == string }
        .next { it.opcode == Opcodes.PUTSTATIC && it.fieldType == String::class.type }
}