package com.openmmo.analysers.Runnables

import com.openmmo.analysers.Frames.AdminPlayerSearchFrame
import com.openmmo.mapper.*

@DependsOn(AdminPlayerSearchFrame::class)
class AdminFramePlayerSearchCloseCallback : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.any { it.className.contains("Runnable") }}
        .and { it.constructors.any { it.arguments.startsWith(type<AdminPlayerSearchFrame>()) } }
}