package com.openmmo.analysers

import com.openmmo.ASMExtensions.hasInterfaces
import com.openmmo.ASMExtensions.isAbstract
import com.openmmo.ASMExtensions.isConstructor
import com.openmmo.mapper.*
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
}