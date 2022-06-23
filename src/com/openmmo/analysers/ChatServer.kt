package com.openmmo.analysers

import com.openmmo.mapper.*

@DependsOn(GameClass::class, AbstractServer::class)
class ChatServer : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("error handling cs message ") }}
        .and { it.superType == type<AbstractServer>() }

    class receivePacket : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.instructionsContainsString("Unknown packet received") }
    }
}