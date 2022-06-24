package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(GameServer::class)
class Captcha : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.fields.any { field -> field.type.className.contains("ByteArrayOutputStream") } }
        .and { it.interfaces.isEmpty() }
        .and { it.constructors[0].arguments.startsWith(type<GameServer>()) }
}