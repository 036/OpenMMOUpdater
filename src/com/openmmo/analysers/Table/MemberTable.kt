package com.openmmo.analysers.Table

import com.openmmo.mapper.*

class MemberTable : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Table")}
        .and { it.methods.any { it.instructionsContainsString("/member-table") } }
}