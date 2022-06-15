package com.openmmo.analysers

import com.openmmo.ASMExtensions.hasInterfaces
import com.openmmo.mapper.ClassWrapper
import com.openmmo.mapper.IdentityMapper
import com.openmmo.mapper.Predicate
import com.openmmo.mapper.predicateOf
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LdcInsnNode

class BattleClass : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> {
        var foundMessage = false

        it.methods.forEach { method ->
            method.instructions.forEach { instructionWrapper ->

                if (instructionWrapper.node is LdcInsnNode) {
                    if(instructionWrapper.ldcCst.toString().contains("Malformed battle slot status request")) foundMessage = true
                    if((instructionWrapper.node as LdcInsnNode).cst.equals("Malformed battle slot status request")) foundMessage = true
                }
            }
        }

        foundMessage
    }
}