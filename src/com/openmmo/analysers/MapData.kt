package com.openmmo.analysers

import com.openmmo.ASMExtensions.isAbstract
import com.openmmo.ASMExtensions.isConstructor
import com.openmmo.mapper.*
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode

class MapData : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> {it.constructors.size == 1}
        .and { it.constructors.first().arguments.size == 4 }
        .and{ it.constructors.first().arguments.startsWith(Type.BYTE_TYPE) }
        .and { it.interfaces.any {iface -> iface.className.contains("Disposable")} }

    @DependsOn(FooterPosition::class)
    class GetFooterFieldInDirection : IdentityMapper.Method() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.startsWith(type<FooterPosition>()) }
            .and { it.arguments.endsWith(Type.INT_TYPE) }
            .and { it.arguments.size == 3 }
    }
}