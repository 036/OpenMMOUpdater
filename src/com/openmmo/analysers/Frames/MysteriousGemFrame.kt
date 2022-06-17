package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import org.objectweb.asm.Type

@DependsOn(AbstractResizableFrame::class)
class MysteriousGemFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<AbstractResizableFrame>()}
        .and { it.methods.any { it.instructionsContainsString("mysterious-gem") } }
        .and { it.methods.any { it.instructionsContainsString("seed-plant-dialog") } }
        .and { it.constructors.any { it.arguments.size == 2 && it.arguments.endsWith(Type.INT_TYPE) } }
}