package com.openmmo.analysers.Packets

import com.openmmo.mapper.*

class PacketKeyExchange : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("SHA256withECDSA") }}
        .and { it.methods.any { it.instructionsContainsString("Failed to load public key.") } }
        .and { it.methods.any { it.instructionsContainsString("Failed to verify signature.") } }

    class PacketKeyExchange : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> {  it.instructionsContainsString("SHA256withECDSA") }
                .and { it.instructionsContainsString("Failed to load public key.")  }
                .and { it.instructionsContainsString("Failed to verify signature.") }
    }
}