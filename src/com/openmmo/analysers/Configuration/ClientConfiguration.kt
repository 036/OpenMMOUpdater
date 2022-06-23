package com.openmmo.analysers.Configuration

import com.openmmo.mapper.*

class ClientConfiguration : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Can't load configuration") }}

    class loginServerNetworkClientPort : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("loginserver.network.client.port") }
    }

    class loginServerNetworkClientHost : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("loginserver.network.client.host") }
    }

    class gameServerSecurityMaxMessageLength : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("gameserver.security.max_message_length") }
    }

    class clientUIHudChatSentMessageMemoryCount : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("client.ui.hud.chat.sent_message_memory_count") }
    }

    class clientUIHudChatSentMessageDisplayCount : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("client.ui.hud.chat.message_display_count") }
    }

    class clientDevAddLatency : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("client.dev.add_latency") }
    }

    class clientDevAddTimeDebugs : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("client.dev.time_debugs") }
    }

    class forceLoginServerPort : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("force.ls.port") }
    }

    class forceLoginServerHost : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("force.ls.host") }
    }

    class forceGameServerPort : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("force.gs.port") }
    }

    class forceGameServerHost : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("force.gs.host") }
    }

    class forceChatServerPort : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("force.cs.port") }
    }

    class forceChatServerHost : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> {it.klass.type == type<ClientConfiguration>()}
            .and { it.node.visibleAnnotations != null }
            .and { it.node.visibleAnnotations.first().values[1].toString().contains("force.cs.host") }
    }
}