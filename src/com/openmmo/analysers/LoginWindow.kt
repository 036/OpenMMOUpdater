package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class LoginWindow : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.constructors.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "logingui" } } }
        .and { it.constructors.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "This platform is now unsupported and the game may break at any time in the future." } } }

    @DependsOn(LoginWindow::class)
    class submit : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "You do not have the minimum required roms to login." } }
    }
}