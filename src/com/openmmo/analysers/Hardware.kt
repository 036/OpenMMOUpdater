package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import java.io.File
import java.lang.reflect.Modifier

class Hardware : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst.equals("xdg-open") } } }

    class getHwid : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.instructions.filter { it.opcode == Opcodes.INVOKEVIRTUAL }.any { it.methodName.contains("getHostName") } }
    }
}

