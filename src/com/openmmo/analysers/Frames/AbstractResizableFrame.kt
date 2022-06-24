package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import java.lang.reflect.Modifier

@DependsOn(BaseResizableFrame::class)
class AbstractResizableFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access) }
        .and { it.superType == type<BaseResizableFrame>() }
}