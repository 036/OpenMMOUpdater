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

@DependsOn(AbstractPacket::class)
class GetMailPacket : IdentityMapper.Packet(153)

@DependsOn(AbstractPacket::class)
class GetGlobalTradeLinkPacket : IdentityMapper.Packet(155)

@DependsOn(AbstractPacket::class)
class TournamentSignUpOpenPacket : IdentityMapper.Packet(170)

@DependsOn(AbstractPacket::class)
class TournamentSignUpClosePacket : IdentityMapper.Packet(171)

@DependsOn(AbstractPacket::class)
class MatchmakingSignupOpenPacket : IdentityMapper.Packet(74)

@DependsOn(AbstractPacket::class)
class MatchmakingSignupClosePacket : IdentityMapper.Packet(71)