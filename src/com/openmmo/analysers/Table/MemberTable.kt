package com.openmmo.analysers.Table

import com.openmmo.mapper.*

@DependsOn(BaseTable::class)
class MemberTable1 : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseTable>()}
        .and { it.methods.any { it.instructionsContainsString("/member-table") } }
        .and { it.constructors.any { it.arguments.size == 0 } }
}

@DependsOn(BaseTable::class)
class MemberTable2 : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseTable>()}
        .and { it.methods.any { it.instructionsContainsString("/member-table") } }
        .and { it.constructors.any { it.arguments.size == 1 } }
}

@DependsOn(BaseTable::class)
class MemberTable3 : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseTable>()}
        .and { it.methods.any { it.instructionsContainsString("/member-table") } }
        .and { it.constructors.any { it.arguments.size == 2 } }
}