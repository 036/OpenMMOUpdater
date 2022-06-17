package com.openmmo.analysers.Runnables

import com.openmmo.mapper.*

class OpenForumsClientCustomizationRunnable : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("https://forums.pokemmo.eu/index.php?/forum/33-client-customization/")}}
}