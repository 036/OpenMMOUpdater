package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class CaptchaWindow : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.size == 1 }
        .and { it.constructors.any { it.arguments.endsWith(Type.BYTE_TYPE)} }
        .and { it.constructors.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "confirm-widget" } } }
        .and { it.constructors.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "countdown-progressbar" } } }
        .and { it.instanceMethods.any { it.arguments.startsWith(Type.BOOLEAN_TYPE) } }

    @DependsOn(CaptchaWindow::class)
    class submitCaptcha : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.startsWith(Type.BOOLEAN_TYPE) }
    }

    class paint : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.name == "paint" }
    }
}