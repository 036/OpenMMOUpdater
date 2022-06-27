package com.openmmo.analysers.security

import com.openmmo.analysers.Roms.GBARom
import com.openmmo.analysers.Utility.ILogger
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

@DependsOn(HashClass::class)
class NoHash : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<HashClass>() }
        .and { it.instanceMethods.filter { it.instructions.count() < 10 }.size == 3 }
        .and { it.instanceMethods.any { it.returnType == Type.INT_TYPE && it.arguments.isEmpty()} }
        .and { it.instanceMethods.any { it.returnType == Type.VOID_TYPE && it.arguments.size == 3} }
        .and { it.instanceMethods.any { it.returnType == Type.BOOLEAN_TYPE && it.arguments.size == 3} }

    @DependsOn(ILogger::class)
    class logger : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ILogger>() }
            .and { it.klass.type == type<NoHash>() }
    }

    class Mac1 : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type.className.contains("Mac") }[0] == it }
    }

    class Mac2 : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type.className.contains("Mac") }[1] == it }
    }

    class buffer1 : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type.className.contains("ByteBuffer") }[0] == it }
    }

    class buffer2 : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.instanceFields.filter { it.type.className.contains("ByteBuffer") }[1] == it }
    }

    class HashID : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.INT_TYPE }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
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