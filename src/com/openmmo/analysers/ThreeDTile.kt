package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

//@DependsOn(FooterPosition::class)
//class ThreeDTile : IdentityMapper.Class() {
//    override val predicate = predicateOf<ClassWrapper> { it.interfaces.any { iface -> iface.className.contains("Cloneable") } }
//        .and { it.constructors.endsWith(Type.BYTE_TYPE) }
//        .and {  }
//}