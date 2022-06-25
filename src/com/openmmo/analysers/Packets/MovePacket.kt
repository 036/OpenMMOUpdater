package com.openmmo.analysers.Packets

import com.openmmo.analysers.AbstractPacket
import com.openmmo.mapper.*

@DependsOn(AbstractPacket::class)
class MovePacket : IdentityMapper.Packet(6)