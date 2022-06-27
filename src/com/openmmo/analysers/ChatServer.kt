package com.openmmo.analysers

import com.openmmo.analysers.Networking.GenericServer
import com.openmmo.mapper.*

@DependsOn(GameClass::class, GenericServer::class, ConnectToServers::class, GameServer::class)
class ChatServer : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.fields.any { it.type == type<GameClass>() }}
        .and { it.type != type<GameServer>() }
        .and { it.superType == type<GenericServer>() }
        .and { it.constructors[0].arguments.endsWith(type<ConnectToServers>()) }


    class receivePacket : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.instructionsContainsString("Unknown packet received") }
    }
}