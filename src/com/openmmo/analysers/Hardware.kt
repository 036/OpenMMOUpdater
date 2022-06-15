package com.openmmo.analysers

import com.openmmo.mapper.*
import java.io.File
import java.lang.reflect.Modifier
//
//class Hardware : IdentityMapper.Class() {
//    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access) }
//        .and { it.interfaces.isEmpty() }
//        .and { it.fields.isEmpty() }
//        .and { it.methods.filter { method -> method.returnType == ByteArray::class.type }.size == 1}
//}