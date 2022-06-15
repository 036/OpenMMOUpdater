package com.openmmo.analysers

import com.openmmo.mapper.*
import java.lang.reflect.Modifier

class PokeMMOClassMapper : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { Modifier.isAbstract(it.access) }
        .and { it.methods.count() > 7 }
        .and { it.instanceFields.isEmpty() }
        .and { it.staticFields.count() > 24 }
        .and { it.fields.any { field -> field.desc.contains("Multiplexer") } }

    @DependsOn(World::class)
    class world : StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<World>() }
    }

    class inputMultiplexer : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.desc.contains("Multiplexer") }
    }

    @DependsOn(BattleClass::class)
    class battle : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<BattleClass>() }
    }


}