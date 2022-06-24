package com.openmmo.analysers

import com.openmmo.mapper.*

@DependsOn(GameClass::class, AbstractServer::class, ConnectToServers::class, GameServer::class)
class ChatServer : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.fields.any { it.type == type<GameClass>() }}
        .and { it.type != type<GameServer>() }
        .and { it.superType == type<AbstractServer>() }
        .and { it.constructors[0].arguments.endsWith(type<ConnectToServers>()) }


    class receivePacket : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.instructionsContainsString("Unknown packet received") }
    }
}