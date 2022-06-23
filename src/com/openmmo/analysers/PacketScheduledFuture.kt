package com.openmmo.analysers

import com.openmmo.mapper.*

class PacketScheduledFuture : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.staticFields.size == 2}
        .and { it.methods.any { it.returnType.className.contains("ScheduledFuture") } }
}