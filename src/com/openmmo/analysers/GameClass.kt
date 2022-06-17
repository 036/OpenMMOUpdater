package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.lang.reflect.Modifier

class GameClass : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.fields.count() > 30 }
        .and { Modifier.isAbstract(it.access) }
        .and { it.fields.any{field -> field.desc.contains("AtomicBoolean") }}

    class Logout() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.isEmpty() }
            .and { it.returnType == Type.VOID_TYPE }
            .and { it.instructions.filter { ins -> ins.opcode == Opcodes.INVOKEINTERFACE && ins.methodName == "postRunnable"}.toList().size == 3 }
    }

    @DependsOn(InventoryTypeEnum::class)
    class GetItemsFromInventory() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.size == 1 }
            .and { it.arguments.first() == type<InventoryTypeEnum>() }
    }
}