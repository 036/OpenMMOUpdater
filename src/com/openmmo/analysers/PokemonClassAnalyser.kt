package com.openmmo.analysers

import com.openmmo.ASMExtensions.hasInterfaces
import com.openmmo.ASMExtensions.isAbstract
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LdcInsnNode

class PokemonClassAnalyser : Analyser() {
    override var readableName = "PokemonClass"
    val expectedFieldCount = 5

    override fun canRun(node: ClassNode): Boolean {
        if (node.hasInterfaces() && node.isAbstract()) return false;

        var matchingReturnType = false;
        var fieldCount = node.fields.count();
        node.methods.forEach {
            if (it.desc.equals("()Ljava/lang/String;")) matchingReturnType = true;
            it.instructions.forEach {
                if (it is LdcInsnNode) {
                    if (it.cst.equals("???") && (it.next.opcode ==  Opcodes.ARETURN) && matchingReturnType && fieldCount == expectedFieldCount) return true
                }
            }
        }

        return false;
    }

    override fun analyse(node: ClassNode) {

    }
}