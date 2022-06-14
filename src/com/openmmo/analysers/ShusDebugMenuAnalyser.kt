package com.openmmo.analysers

import com.openmmo.ASMExtensions.hasInterfaces
import com.openmmo.ASMExtensions.isAbstract
import com.openmmo.ASMExtensions.isConstructor
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LdcInsnNode

class ShusDebugMenuAnalyser : Analyser() {
    override var readableName = "ShusDebugMenu"

    override fun canRun(node: ClassNode): Boolean {
        if (node.hasInterfaces() && node.isAbstract()) return false;

        node.methods.forEach {
            if (it.isConstructor()) {
                it.instructions.forEach {
                    if (it is LdcInsnNode) {
                        if (it.cst.equals("test")) return true;
                    }
                }
            }
        }

        return false;
    }

    override fun analyse(node: ClassNode) {
    }
}