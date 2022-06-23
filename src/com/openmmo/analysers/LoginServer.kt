package com.openmmo.analysers

import com.openmmo.mapper.*

class LoginServer : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> {
        it.fields.any { field -> field.desc.contains("ArrayDeque") }}
            .and{it.instanceFields.count() == 4}
            .and{it.instanceMethods.count() == 8}

}