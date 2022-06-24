package com.openmmo.analysers.Frames

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

@DependsOn(BaseResizableFrame::class)
class NewDeviceVerificationFrame : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseResizableFrame>()}
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "login-window" } } }
}