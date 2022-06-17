package com.openmmo.analysers.Runnables

import com.openmmo.mapper.*

class ForgotUsernameRunnable : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.any { it.className.contains("Runnable") }}
        .and{ it.methods.any { it.instructionsContainsString("https://pokemmo.eu/account_forgot_username/?local=") }}
}