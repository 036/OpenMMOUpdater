package com.openmmo.analysers.Table

import com.openmmo.mapper.*

@DependsOn(BaseTable::class)
class TournamentTable : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType == type<BaseTable>()}
        .and { it.methods.any { it.instructionsContainsString("/tournament-table") } }
}