package com.openmmo.analysers.Runnables

import com.openmmo.mapper.*
import java.lang.reflect.Modifier

class MainFeedConnectionAbstract : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access)}
        .and { it.methods.any { it.instructionsContainsString("ip_sea") } }
}