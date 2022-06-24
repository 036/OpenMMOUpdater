package com.openmmo.analysers

import com.openmmo.mapper.*

class ErrorReportHttpListener : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructionsContainsString("Error report status code: ") } }
}