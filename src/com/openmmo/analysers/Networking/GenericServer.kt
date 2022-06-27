package com.openmmo.analysers.Networking

import com.openmmo.analysers.Packets.PacketEncryption
import com.openmmo.mapper.*
import org.objectweb.asm.Type
import java.lang.reflect.Modifier

class GenericServer : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access)}
        .and { it.instanceFields.any { it.desc.contains("PublicKey") } }
        .and { it.instanceFields.any { it.desc.contains("ArrayDeque") } }
        .and { it.methods.any { it.instructionsContainsString("Wrong checksum: ") } }

    @DependsOn(PacketEncryption::class)
    class packetEncryption : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<PacketEncryption>() }
    }

    class EncryptByteBuffer : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.INT_TYPE }
            .and { it.arguments.size == 1 }
            .and { it.arguments[0].className.contains("ByteBuffer") }
            .and { it.instructionsContainsString("") }
    }
}