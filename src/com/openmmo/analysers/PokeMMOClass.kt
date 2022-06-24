package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.lang.reflect.Modifier

class PokeMMOClass : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { Modifier.isAbstract(it.access) }
        .and { it.methods.count() > 7 }
        .and { it.instanceFields.isEmpty() }
        .and { it.staticFields.count() > 24 }
//        .and { it.fields.any { field -> field.desc.contains("Multiplexer") } }

    @DependsOn(World::class)
    class world : StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<World>() }
    }

    @DependsOn(PokeMMOClass::class, InputMultiplexer::class)
    class inputMultiplexer : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<InputMultiplexer>() }
    }

    @DependsOn(BattleClass::class)
    class battle : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<BattleClass>() }
    }

    @DependsOn(Hardware::class)
    class hardware : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.type == type<PokeMMOClass>() }
            .and { it.type == type<Hardware>() }
    }

    class openSupportUrl : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.klass.type == type<PokeMMOClass>() }
            .and { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst.equals("https://support.pokemmo.eu/auth/") } }
    }


}