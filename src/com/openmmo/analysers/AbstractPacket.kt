package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type
import java.lang.reflect.Modifier
import java.nio.ByteBuffer

@DependsOn(GameServer::class)
class AbstractPacket : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { Modifier.isAbstract(it.access) }
        .and { it.fields.isEmpty() }
        .and { it.constructors.size == 1 }
        .and { it.constructors[0].arguments.startsWith(Type.INT_TYPE) }
        .and { it.methods.any { Modifier.isAbstract(it.access) } }
        .and { it.methods.any { it.returnType == Type.VOID_TYPE } }
        .and { it.methods.any { it.arguments.endsWith(ByteBuffer::class.java.type) && it.arguments.startsWith(type<GameServer>()) } }
}