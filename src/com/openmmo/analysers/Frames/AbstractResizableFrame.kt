package com.openmmo.analysers.Frames

import com.openmmo.mapper.*

class AbstractResizableFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.constructors.size == 2 }
        .and { it.superType.className.contains("ResizableFrame") }
}