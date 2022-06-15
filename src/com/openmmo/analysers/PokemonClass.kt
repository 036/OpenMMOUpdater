package com.openmmo.analysers

import com.openmmo.ASMExtensions.hasInterfaces
import com.openmmo.ASMExtensions.isAbstract
import com.openmmo.mapper.ClassWrapper
import com.openmmo.mapper.IdentityMapper
import com.openmmo.mapper.and
import com.openmmo.mapper.predicateOf
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LdcInsnNode
import java.lang.reflect.Modifier

class PokemonClass : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { !Modifier.isAbstract(it.access) }
        .and { it.fields.size == 5}
        .and {
            var foundMessage = false
            it.methods.forEach { method ->
                method.instructions.forEach { instruction ->
                    if (instruction.node is LdcInsnNode) {
                        if (instruction.ldcCst.equals("???")) foundMessage = true
                    }
                }
            }

            foundMessage
        }
}