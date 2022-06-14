package com.openmmo.analysers

import com.openmmo.ASMExtensions.hasInterfaces
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LdcInsnNode

class BattleClassAnalyser : Analyser() {
    override var readableName = "BattleClass"

    override fun canRun(node: ClassNode): Boolean {
        if (node.hasInterfaces()) return false;

        node.methods.forEach {
            it.instructions.forEach {
                if (it is LdcInsnNode) {
                    if (it.cst.equals("Malformed battle slot status request ")) return true;
                }
            }
        }
        return false;
    }

    override fun analyse(node: ClassNode) {

    }
}