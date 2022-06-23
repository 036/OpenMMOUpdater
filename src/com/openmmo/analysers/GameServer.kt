package com.openmmo.analysers

import com.openmmo.ASMExtensions.hasInterfaces
import com.openmmo.ASMExtensions.isAbstract
import com.openmmo.ASMExtensions.isConstructor
import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LdcInsnNode

@DependsOn(GameClass::class)
class GameServer : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.fields.any{field -> field.desc.contains("ArrayDeque")}
        .and( it.fields.any { field -> field.desc.contains("Inflater") })
        .and( it.fields.any { field -> field.desc.contains("ScheduledFuture") })
        .and(it.instanceFields.any { field -> field.type == type<GameClass>() })
        .and( it.instanceFields.count() == 6)
        .and(it.instanceMethods.count() == 10)}

    @DependsOn(GameClass::class)
    class gameClass : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<GameClass>() }
    }

    @DependsOn(GameServer::class)
    class sendPacket : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.klass.type == type<GameServer>() }
            .and { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 1 }
            .and { it.invokesMethod(Opcodes.INVOKEVIRTUAL, "addLast", "java/util/ArrayDeque") }
            .and { it.instructions.any { it.opcode == Opcodes.MONITOREXIT } }
            .and { !it.hasAccess(Opcodes.ACC_SYNTHETIC) }
    }

    @DependsOn(GameServer::class)
    class packetQueue : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.type == type<GameServer>() }
            .and { it.type.className.contains("ArrayDeque") }
    }

    @DependsOn(GameServer::class)
    class inflater : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.type == type<GameServer>() }
            .and { it.type.className.contains("Inflater") }
    }

    @DependsOn(GameServer::class)
    class scheduledFuture : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.type == type<GameServer>() }
            .and { it.type.className.contains("ScheduledFuture") }
    }

    @DependsOn(GameServer::class)
    class handleGameServerMessage : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.BOOLEAN_TYPE }
            .and { it.arguments.size == 1 }
            .and { it.instructionsContainsString("error handling gs message ") }
    }
}