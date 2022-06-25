package com.openmmo.analysers.Packets

import com.openmmo.analysers.AbstractPacket
import com.openmmo.mapper.*

@DependsOn(AbstractPacket::class)
class MovePacket : IdentityMapper.Packet(6)

@DependsOn(AbstractPacket::class)
class ChangeDirectionPacket : IdentityMapper.Packet(7)

@DependsOn(AbstractPacket::class)
class OpenGiftShopPacket : IdentityMapper.Packet(112)

@DependsOn(AbstractPacket::class)
class SelectPokemonFollowPacket : IdentityMapper.Packet(17)

@DependsOn(AbstractPacket::class)
class GetTournamentsPacket : IdentityMapper.Packet(117)