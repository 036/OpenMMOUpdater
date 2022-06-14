package com.openmmo.ASMExtensions

import org.objectweb.asm.tree.ClassNode
import java.lang.reflect.Modifier

fun ClassNode.hasInterfaces(): Boolean {
    return this.interfaces.any();
}

fun ClassNode.isAbstract(): Boolean {
    return Modifier.isAbstract(this.access)
}
