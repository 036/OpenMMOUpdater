package com.openmmo.analysers.Packets

import com.openmmo.mapper.*

class PacketEncryption : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("AES/CTR/NoPadding") }}
}