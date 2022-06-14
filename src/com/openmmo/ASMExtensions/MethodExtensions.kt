package com.openmmo.ASMExtensions

import org.objectweb.asm.tree.MethodNode

fun MethodNode.isConstructor(): Boolean {
    return this.name.equals("<init>");
}

