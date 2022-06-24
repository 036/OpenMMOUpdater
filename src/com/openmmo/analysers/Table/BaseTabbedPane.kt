package com.openmmo.analysers.Table

import com.openmmo.mapper.*

class BaseTabbedPane : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsMatchesString("tabbedpane") }}
        .and { it.methods.any { it.instructionsMatchesString("tabPosition") }}
        .and { it.methods.any { it.instructionsMatchesString("direction") }}
}