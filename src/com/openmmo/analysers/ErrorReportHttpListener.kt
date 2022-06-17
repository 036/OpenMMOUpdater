package com.openmmo.analysers

import com.openmmo.mapper.*

class ErrorReportHttpListener : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.any { it.className.contains("HttpResponseListener") }}
        .and { it.methods.any { it.instructionsContainsString("Error report status code: ") } }
}