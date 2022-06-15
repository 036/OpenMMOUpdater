package com.openmmo.analysers

import com.openmmo.mapper.ClassWrapper
import com.openmmo.mapper.IdentityMapper
import com.openmmo.mapper.predicateOf

class GameEntryAppListenerMapper : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any{method -> method.name.equals("pause")}
        .and(it.methods.any { method -> method.name.equals("create") })
        .and(it.fields.any{field -> field.desc.contains("TWLDynamicImage")})}
}