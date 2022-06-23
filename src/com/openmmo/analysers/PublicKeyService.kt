package com.openmmo.analysers

import com.openmmo.mapper.*
import java.lang.reflect.Modifier

class PublicKeyService : IdentityMapper.Class() {
    val publicKey1 = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEtqx2myJz3ftlYWgd7cbNqf2t208itQMY7ouPNBDpQetbi7eXbEDxDDZy4Q9fMnI6mF5/D0qMdRd40SRXf0OS7Q=="
    val publicKey2 = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEh4Vqgnd+8Fqebu0H40v+FgwhE6RwgAYxJMihb8mJmcHDy8r/rPz3kLHH1oabyKIRUa5Y2cK0TsxZky+mp7DKWA=="

    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access)}
        .and { it.staticMethods.size == 2 }
        .and{ it.methods.all { it.returnType.className.contains("PublicKey") }}
        .and { it.methods.any { it.instructionsContainsString(publicKey1) } }
        .and { it.methods.any { it.instructionsContainsString(publicKey2) } }
}