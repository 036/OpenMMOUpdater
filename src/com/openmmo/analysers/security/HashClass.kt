package com.openmmo.analysers.security

import com.openmmo.analysers.Roms.GBARom
import com.openmmo.analysers.Utility.ILogger
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class HashClass : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.instanceMethods.filter { it.hasAccess(Opcodes.ACC_ABSTRACT) }.size == 3 }
        .and { it.instanceMethods.any { it.returnType == Type.INT_TYPE && it.arguments.isEmpty()} }
        .and { it.instanceMethods.any { it.returnType == Type.VOID_TYPE && it.arguments.size == 3} }
        .and { it.instanceMethods.any { it.returnType == Type.BOOLEAN_TYPE && it.arguments.size == 3} }

    @DependsOn(ILogger::class)
    class logger : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ILogger>() }
            .and { it.klass.type == type<HashClass>() }
    }

    class GetHashID : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.INT_TYPE }
            .and { it.arguments.isEmpty() }
            .and { it.hasAccess(Opcodes.ACC_ABSTRACT) }
    }

    class SetHash : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 3 }
            .and { it.arguments[0].baseType == Type.BYTE_TYPE}
            .and { it.arguments[1].baseType == Type.INT_TYPE}
            .and { it.arguments[2].baseType == Type.INT_TYPE}
            .and { it.hasAccess(Opcodes.ACC_ABSTRACT) }
    }

    class DoesHashMatch : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.BOOLEAN_TYPE }
            .and { it.arguments.size == 3 }
            .and { it.arguments[0].baseType == Type.BYTE_TYPE}
            .and { it.arguments[1].baseType == Type.INT_TYPE}
            .and { it.arguments[2].baseType == Type.INT_TYPE}
            .and { it.hasAccess(Opcodes.ACC_ABSTRACT) }
    }

}