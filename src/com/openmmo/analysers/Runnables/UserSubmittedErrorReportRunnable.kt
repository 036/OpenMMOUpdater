package com.openmmo.analysers.Runnables

import com.openmmo.mapper.*

class UserSubmittedErrorReportRunnable : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("User Submitted Error Report") }}
}