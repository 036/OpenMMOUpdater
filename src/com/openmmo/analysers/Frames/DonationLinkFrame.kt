package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(AbstractResizableFrame::class)
class DonationLinkFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<AbstractResizableFrame>() }
        .and { it.methods.any { it.instructionsContainsString("donation-link-dialog") } }
}