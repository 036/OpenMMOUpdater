package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.*
import org.objectweb.asm.tree.LdcInsnNode

class GameLoop : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.equals("com.badlogic.gdx.ApplicationAdapter")}

    @DependsOn(GameLoop::class)
    class pause : IdentityMapper.Method() {
        override val predicate = predicateOf<MethodWrapper> { it.name == "pause" }.and { it.klass.type == type<GameLoop>() }
    }

    @DependsOn(GameLoop::class)
    class create : IdentityMapper.Method() {
        override val predicate = predicateOf<MethodWrapper> { it.name == "create" }.and { it.klass.type == type<GameLoop>() }
    }

    @DependsOn(GameLoop::class)
    class logoutWithError : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.klass.type == type<GameLoop>() }
            .and { it.returnType == Type.VOID_TYPE }
            .and {
                it.instructions.filter { ins -> ins.opcode == Opcodes.INVOKESTATIC }.any { it.methodName == "exit" }
            }
    }
}