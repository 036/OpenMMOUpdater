package com.openmmo.analysers.Networking

import com.openmmo.mapper.*
import java.lang.reflect.Modifier

class DataPacket : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access)}
        .and { it.instanceFields.any { it.desc.contains("PublicKey") } }
        .and { it.instanceFields.any { it.desc.contains("ArrayDeque") } }
        .and { it.methods.any { it.instructionsContainsString("Wrong checksum: ") } }
}