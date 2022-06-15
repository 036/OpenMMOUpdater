package com.openmmo.analysers

import com.openmmo.mapper.*
import java.lang.reflect.Modifier

class GameClass : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.fields.count() > 30 }
        .and { Modifier.isAbstract(it.access) }
        .and { it.fields.any{field -> field.desc.contains("AtomicBoolean") }}
}