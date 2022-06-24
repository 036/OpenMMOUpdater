package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.lang.reflect.Modifier

@DependsOn(BattleStatusEnum::class, PlayerEntity::class, MapManager::class)
class World : IdentityMapper.Class() {
    override val predicate =
        predicateOf<ClassWrapper> { it.fields.any { field -> field.desc.equals("Ljava/util/concurrent/ConcurrentHashMap;") && field.access != 0 && Opcodes.ACC_STATIC != 0 }}
            .and { it.instanceFields.any { it.type == type<MapManager>() }}
            .and { it.interfaces.any{iface -> iface.className.contains("java.lang.Iterable")} }
            .and { it.methods.any { it.arguments.contains(type<BattleStatusEnum>()) }}

    class getSpecificMapData() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.size == 3 }
            .and { it.arguments.startsWith(Type.BYTE_TYPE, Type.BYTE_TYPE, Type.BYTE_TYPE) }
    }

    @DependsOn(MapManager::class)
    class mapManager() : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<MapManager>()}
    }
}