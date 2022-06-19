package com.openmmo.analysers.Table

import com.openmmo.mapper.*

class MemberTable1 : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Table")}
        .and { it.methods.any { it.instructionsContainsString("/member-table") } }
        .and { it.constructors.any { it.arguments.size == 0 } }
}

class MemberTable2 : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Table")}
        .and { it.methods.any { it.instructionsContainsString("/member-table") } }
        .and { it.constructors.any { it.arguments.size == 1 } }
}

class MemberTable3 : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Table")}
        .and { it.methods.any { it.instructionsContainsString("/member-table") } }
        .and { it.constructors.any { it.arguments.size == 2 } }
}