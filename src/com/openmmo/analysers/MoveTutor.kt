package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class MoveTutor : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("DialogLayout") }
        .and { it.constructors.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "" } } }
        .and { it.constructors.any { it.instructions.any { it.opcode == Opcodes.LDC && it.ldcCst == "battle-panel-dark" } } }
        .and { it.constructors.any { it.arguments.size == 2 } }

    @DependsOn(MoveTutor::class)
    class learningMoveToSlot : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.startsWith(Type.BYTE_TYPE) }
            .and {
                it.instructions.filter{ it.opcode == Opcodes.INVOKEVIRTUAL }.count { it.methodName == "hideTooltip" } == 0
            }
    }

    @DependsOn(MoveTutor::class)
    class learningMoveToSlotWithToolTip : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.startsWith(Type.BYTE_TYPE) }
            .and {
                it.instructions.filter{ it.opcode == Opcodes.INVOKEVIRTUAL }.count { it.methodName == "hideTooltip" } == 1
            }
    }

}