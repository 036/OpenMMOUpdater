package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.lang.reflect.Modifier

class MapManager : IdentityMapper.Class() {
    override val predicate =
        predicateOf<ClassWrapper> { it.fields.any { field -> field.name.equals("serialVersionUID")}}
            .and { it.constructors.any { it.instructions.any { it.opcode == Opcodes.SIPUSH && it.intOperand == 4096 } } }

    class loadedMaps() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.arrayDimensions == 1 }
    }
}