package com.openmmo.analysers.Packets

import com.openmmo.analysers.Utility.ILogger
import com.openmmo.analysers.security.HashClass
import com.openmmo.analysers.security.NoHash
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class PacketEncryption : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("AES/CTR/NoPadding") }}

    @DependsOn(ILogger::class)
    class logger : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ILogger>() }
            .and { it.klass.type == type<PacketEncryption>() }
    }

    @DependsOn(HashClass::class)
    class hash1 : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type == type<HashClass>() }[0] == it }
    }

    @DependsOn(HashClass::class)
    class hash2 : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type == type<HashClass>() }[1] == it }
    }

    class cipher1 : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type.className.contains("Cipher") }[0] == it }
    }

    class cipher2 : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type.className.contains("Cipher") }[1] == it }
    }

    class HashTwoByteArrays : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType.baseType == Type.BYTE_TYPE }
            .and { it.arguments.size == 2 }
            .and { it.arguments[0].baseType == Type.BYTE_TYPE}
            .and { it.arguments[1].baseType == Type.BYTE_TYPE}
            .and { it.hasAccess(Opcodes.ACC_STATIC) }
    }
}