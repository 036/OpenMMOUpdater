package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

@DependsOn(GameClass::class)
class ConnectToServers : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.instanceFields.size == 7 }
        .and { it.instanceFields.filter { it.type == Type.BOOLEAN_TYPE }.size == 2 }
        .and { it.instanceFields.any { it.type == type<GameClass>() } }
}