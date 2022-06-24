package com.openmmo.analysers.Dialogs

import com.openmmo.mapper.*

class BaseDialog : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsMatchesString("dialoglayout") }}
        .and { it.methods.any { it.instructionsMatchesString("Can't add group from different layout") } }
}