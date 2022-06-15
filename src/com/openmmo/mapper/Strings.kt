package com.openmmo.mapper

class Strings : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.staticFields.count { it.type == String::class.type } > 20 }
}