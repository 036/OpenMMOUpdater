package com.openmmo.mapper

import org.objectweb.asm.Type
import org.objectweb.asm.tree.FieldNode

class FieldWrapper(val jar: JarWrapper, val klass: ClassWrapper, val node: FieldNode) {

    val name: String get() = node.name

    val access get() = node.access

    val desc: String get() = node.desc

    val type: Type = Type.getType(desc)

    val id = klass.type to name

    fun hasAccess(mask: Int): Boolean {
        return access and mask != 0;
    }

    override fun toString(): String {
        return "$klass.$name"
    }
}