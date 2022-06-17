package com.openmmo.analysers

import com.openmmo.analysers.Enums.SoundEnum
import com.openmmo.mapper.*
import org.objectweb.asm.Type

@DependsOn(SoundEnum::class)
class AudioHandler : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.instanceFields.any { it.type == type<SoundEnum>() }}
        .and { it.constructors.any { it.arguments.startsWith(Type.BYTE_TYPE, Type.SHORT_TYPE) } }
}