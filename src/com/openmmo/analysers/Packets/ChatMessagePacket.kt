package com.openmmo.analysers.Packets

import com.openmmo.analysers.AbstractPacket
import com.openmmo.analysers.ChatTypeEnum
import com.openmmo.mapper.*

@DependsOn(AbstractPacket::class, ChatTypeEnum::class)
class ChatMessagePacket : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<AbstractPacket>()}
        .and { it.fields.any { it.type == type<ChatTypeEnum>() } }
        .and { it.constructors[0].arguments.startsWith(type<ChatTypeEnum>()) }
}