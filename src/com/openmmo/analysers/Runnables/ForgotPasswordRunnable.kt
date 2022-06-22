package com.openmmo.analysers.Runnables

import com.openmmo.mapper.*

class ForgotPasswordRunnable : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Runnable")}
        .and { it.methods.any { it.instructionsContainsString("https://pokemmo.eu/account_forgot_password/?local=") } }
}