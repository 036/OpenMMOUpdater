package com.openmmo.analysers

import com.openmmo.ASMExtensions.hasInterfaces
import com.openmmo.ASMExtensions.isAbstract
import com.openmmo.ASMExtensions.isConstructor
import com.openmmo.mapper.*
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LdcInsnNode
import java.lang.reflect.Modifier

class ShusDebugMenu : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> {
        var foundMessage = false

        it.methods.forEach { method ->
            if (method.isConstructor) {
                method.instructions.forEach { instructionWrapper ->
                    if (instructionWrapper.node is LdcInsnNode) {
                        if((instructionWrapper.node as LdcInsnNode).cst.equals("test")) foundMessage = true
                    }
                }
            }
        }

        foundMessage
    }
        .and { it.interfaces.isEmpty() }
        .and { !Modifier.isAbstract(it.access) }

    class open() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.size == 1 }
    }


}