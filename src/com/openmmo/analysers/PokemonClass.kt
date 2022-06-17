package com.openmmo.analysers

import com.openmmo.mapper.*
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

    @DependsOn(GameLoop::class)
    class gameLoop() : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<GameLoop>() }
    }
}