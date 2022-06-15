package com.openmmo.analysers

import com.openmmo.ASMExtensions.isConstructor
import com.openmmo.mapper.*
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode
import java.lang.reflect.Modifier

class FooterPosition : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access) }
        .and { !it.interfaces.any() }
        .and { it.instanceFields.any { it.desc.contains("Array") } }
        .and { it.instanceFields.size == 3 }
        .and { it.constructors.first().arguments.size == 2}
        .and { it.constructors.first().arguments.startsWith(Type.SHORT_TYPE)}
        .and { it.constructors.first().arguments.endsWith(Type.SHORT_TYPE)}
}