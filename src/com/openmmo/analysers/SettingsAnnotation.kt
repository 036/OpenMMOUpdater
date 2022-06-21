package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes

// TODO: Add better way to identify Annotations
class SettingsAnnotation : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.name.startsWith("f/") }
        .and { it.methods.any { it.name == "key" && it.returnType.className.contains("String")} }
        .and { it.methods.any { it.name == "defaultValue" && it.returnType.className.contains("String")} }
        .and { it.hasAccess(Opcodes.ACC_ABSTRACT) && it.hasAccess(Opcodes.ACC_ANNOTATION) && it.hasAccess(Opcodes.ACC_INTERFACE) }
}