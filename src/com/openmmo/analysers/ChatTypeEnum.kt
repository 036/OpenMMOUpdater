package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class ChatTypeEnum : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.staticFields.size == 14 }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "/normal" } } }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "/all" } } }

    @DependsOn(ChatTypeEnum::class)
    class valueOf : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.name == "valueOf" }
            .and { it.klass.type == type<ChatTypeEnum>() }
    }
}