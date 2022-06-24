package com.openmmo.analysers

import com.openmmo.ASMExtensions.isConstructor
import com.openmmo.mapper.*
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode
import java.lang.reflect.Modifier

class FooterPosition : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access) }
        .and { !it.interfaces.any() }
        .and { it.instanceFields.size == 3 }
        .and { it.constructors.first().arguments.size == 2}
        .and { it.constructors.first().arguments.startsWith(Type.SHORT_TYPE)}
        .and { it.constructors.first().arguments.endsWith(Type.SHORT_TYPE)}

    class getX : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.isEmpty() }
            .and { it.returnType == Type.SHORT_TYPE }
            .and { it.klass.methods.filter { m -> m.returnType == Type.SHORT_TYPE && m.arguments.isEmpty() }[0] == it }
    }

    class getY : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.isEmpty() }
            .and { it.returnType == Type.SHORT_TYPE }
            .and { it.klass.methods.filter { m -> m.returnType == Type.SHORT_TYPE && m.arguments.isEmpty() }[1] == it }
    }

    class getZ : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.isEmpty() }
            .and { it.returnType == Type.SHORT_TYPE }
            .and { it.klass.methods.filter { m -> m.returnType == Type.SHORT_TYPE && m.arguments.isEmpty() }[2] == it }
    }


}