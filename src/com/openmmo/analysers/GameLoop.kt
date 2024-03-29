package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.*
import org.objectweb.asm.tree.LdcInsnNode
import java.lang.Exception
import java.lang.reflect.Modifier

@DependsOn(WindowManager::class)
class GameLoop : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.instanceFields.any { it.type == type<WindowManager>() }}
        .and { it.fields.any { it.type.descriptor.contains("HashMap") } }
        .and { Modifier.isAbstract(it.access) }
        .and { it.instanceFields.size > 20 }

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

    @DependsOn(GameLoop::class)
    class getPathToTheme: IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.klass.type == type<GameLoop>()}
            .and { it.returnType.className.contains("URL") }
            .and { it.arguments.count() == 2 }
    }

    @DependsOn(GameLoop::class)
    class showException : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.klass.type == type<GameLoop>()}
            .and { it.arguments.count() == 1 }
            .and { it.arguments.first().className.contains("Exception") }
    }


    class loadTheme : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType.className.contains("ThemeManager") }
            .and { it.arguments.size == 2 }
    }

    class getWorldWidth : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.INT_TYPE }
            .and { it.arguments.isEmpty() }
            .and { it.invokesMethod(Opcodes.INVOKEVIRTUAL, "getWorldWidth", "com/badlogic/gdx/utils/viewport/Viewport") }
    }

    class getWorldHeight : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.returnType == Type.INT_TYPE }
            .and { it.arguments.isEmpty() }
            .and { it.invokesMethod(Opcodes.INVOKEVIRTUAL, "getWorldHeight", "com/badlogic/gdx/utils/viewport/Viewport") }
    }
}