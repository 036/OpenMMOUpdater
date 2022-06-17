package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.lang.reflect.Modifier

class WindowManager : IdentityMapper.Class() {
    override val predicate =
        predicateOf<ClassWrapper> { it.constructors.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "maingui" } } }
            .and { it.constructors.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "tooltip-help" } } }
            .and { it.constructors.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "-cursor" } } }

    @DependsOn(WindowManager::class)
    class instance() : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<WindowManager>() }
    }

    @DependsOn(Evolution::class)
    class evolution() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<Evolution>() }
    }

    @DependsOn(LoginWindow::class)
    class loginWindow() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<LoginWindow>() }
    }
}