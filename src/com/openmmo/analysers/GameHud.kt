package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

class GameHud : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.instanceFields.size > 20 }
        .and { it.constructors.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "hudgui" } } }

    @DependsOn(BreedWindow::class)
    class breedingWindow : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<BreedWindow>() }
    }
}