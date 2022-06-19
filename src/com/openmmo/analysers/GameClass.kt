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

    @DependsOn(InventoryTypeEnum::class, ItemInventory::class)
    class GetInventory() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.size == 1 }
            .and { it.arguments.first() == type<InventoryTypeEnum>() }
            .and { it.returnType == type<ItemInventory>()}
    }

    @DependsOn(ItemInventory::class, currentInventory::class)
    class GetCurrentInventory() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.isEmpty() }
            .and { it.returnType == type<ItemInventory>()}
            .and { it.accessesField(Opcodes.GETFIELD, field<currentInventory>())}
    }

    @DependsOn(InventoryTypeEnum::class)
    class currentInventory() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<InventoryTypeEnum>() }
    }

    @DependsOn(ItemInventory::class)
    class itemInventories() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.sort == Type.ARRAY && it.type.baseType == type<ItemInventory>() }
    }

    @DependsOn(PokemonInventory::class)
    class pokemonInventories() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.sort == Type.ARRAY && it.type.baseType == type<PokemonInventory>() }
    }

    @DependsOn(PokemonInventory::class)
    class currentPokemonInventory() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<PokemonInventory>() }
    }

    @DependsOn(World::class)
    class world() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<World>() }
    }

    @DependsOn(Account::class)
    class account() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<Account>() }
    }

    @DependsOn(GameServer::class)
    class gameServer() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<GameServer>() }
    }

    @DependsOn(Account::class, account::class)
    class GetAccount() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == type<Account>() }
            .and { it.arguments.isEmpty() }
            .and { it.accessesField(Opcodes.GETFIELD, field<account>())}
    }

    @DependsOn(PokemonInventory::class)
    class OpenInventory() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == type<PokemonInventory>() }
            .and { it.arguments.size == 1 }
    }

    @DependsOn(PokemonSlot::class)
    class ExchangePokemonSlots() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 2 }
            .and { it.arguments[0] == type<PokemonSlot>() }
            .and { it.arguments[1] == type<PokemonSlot>() }
    }

    @DependsOn(PokemonSlot::class)
    class UseItemSimpleSynchronous() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 2 }
            .and { it.arguments[0] == Type.SHORT_TYPE }
            .and { it.arguments[1] == Type.BYTE_TYPE }
            .and { it.invokesMethod(Opcodes.INVOKEINTERFACE, "postRunnable", "com/badlogic/gdx/Application") }
    }

    class Move() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 3 }
            .and { it.arguments[0] == Type.BYTE_TYPE }
            .and { it.arguments[1] == Type.BOOLEAN_TYPE }
            .and { it.arguments[2] == Type.BOOLEAN_TYPE }
    }

    class UseItem() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.arguments.size == 6 }
            .and { it.hasAccess(Opcodes.ACC_PUBLIC) }
            .and { it.hasAccess(Opcodes.ACC_ABSTRACT) }
            .and { it.arguments.first() == Type.SHORT_TYPE }
    }

    @DependsOn(UseItem::class)
    class UseItemSimple() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.VOID_TYPE }
            .and { it.hasAccess(Opcodes.ACC_PRIVATE) }
            .and { it.arguments.first() == Type.SHORT_TYPE }
            .and { it.invokesMethod(Opcodes.INVOKEVIRTUAL, method<UseItem>())}
    }

    @DependsOn(ChatTypeEnum::class)
    class SendChatMessage() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.INT_TYPE }
            .and { it.arguments.any { it == type<ChatTypeEnum>() } }
    }

    @DependsOn(PokemonClass.gameLoop::class)
    class IsMovementBlocked() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.BOOLEAN_TYPE }
            .and { it.arguments.isEmpty() }
            .and { it.accessesField(Opcodes.GETSTATIC, field<PokemonClass.gameLoop>()) }
    }
}