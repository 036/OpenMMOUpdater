package com.openmmo.analysers.security

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

@DependsOn(HashClass::class)
class CRC16 : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<HashClass>() }
        .and { it.methods.any { it.instructionsContainsInt(49345)  } }

    class table : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.INT_TYPE }
    }

    @DependsOn(CRC16::class)
    class instance : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<CRC16>() }
    }

    class GetHashID : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.INT_TYPE }
            .and { it.arguments.isEmpty() }
    }

    class SetHash : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 3 }
            .and { it.arguments[0].baseType == Type.BYTE_TYPE}
            .and { it.arguments[1].baseType == Type.INT_TYPE}
            .and { it.arguments[2].baseType == Type.INT_TYPE}
    }

    class DoesHashMatch : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.BOOLEAN_TYPE }
            .and { it.arguments.size == 3 }
            .and { it.arguments[0].baseType == Type.BYTE_TYPE}
            .and { it.arguments[1].baseType == Type.INT_TYPE}
            .and { it.arguments[2].baseType == Type.INT_TYPE}
    }

}