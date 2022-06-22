package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Type

class AccountInformation : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.isEmpty() }
        .and { it.instanceFields.size == 3 }
        .and { it.instanceFields.count { it.type == Type.INT_TYPE} == 2 }
        .and { it.instanceFields.count { it.type == ByteArray::class.java.type} == 1}
        .and { it.constructors[0].arguments.size == 3 }
        .and { it.constructors[0].arguments.startsWith(ByteArray::class.java.type) }

    @DependsOn(AccountInformation::class)
    class accountId : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.klass.type == type<AccountInformation>()}
            .and { it.type == Type.INT_TYPE }
            .and { it.klass.fields[0].name == it.name }
    }
}