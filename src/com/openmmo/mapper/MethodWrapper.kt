package com.openmmo.mapper

import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.tree.MethodNode

class MethodWrapper(val jar: JarWrapper, val klass: ClassWrapper, val node: MethodNode) {

    companion object {
        const val CONSTRUCTOR_NAME = "<init>"
        const val CLASS_INITIALIZER_NAME = "<clinit>"
    }

    val name: String get() = node.name

    val desc: String get() = node.desc

    val type: Type = Type.getMethodType(node.desc)

    val arguments = type.argumentTypes.asList()

    val returnType: Type = type.returnType

    val access get() = node.access

    val signature = name to arguments

    val mark = name to type

    val id = Triple(klass.type, name, type)

    val instructions get() = node.instructions.iterator().asSequence()
        .map { InstructionWrapper(jar, klass, this, it) }

    val isClassInitializer: Boolean get() {
        return name == CLASS_INITIALIZER_NAME
    }

    val isConstructor: Boolean get() {
        return name == CONSTRUCTOR_NAME
    }

    fun hasAccess(mask: Int): Boolean {
        return access and mask != 0;
    }

    override fun toString(): String {
        return "$klass.$name$desc"
    }

    fun instructionsContainsString(searchTerm: String): Boolean {
        return this.instructions.filter { it -> it.opcode == Opcodes.LDC && it.ldcCst.toString().contains(searchTerm) }.any()
    }

    fun instructionsMatchesString(searchTerm: String):  Boolean {
        return this.instructions.filter { it -> it.opcode == Opcodes.LDC && it.ldcCst.toString() == searchTerm }.any()
    }

    fun invokesMethod(opcode: Int, method: MethodWrapper): Boolean {
        return this.instructions.filter { it -> it.opcode == opcode && it.methodName == method.name && it.methodOwner.internalName == method.klass.name }.count() > 0
    }

    fun invokesMethod(opcode: Int, name:String, owner: String): Boolean {
        return this.instructions.filter { it -> it.opcode == opcode && it.methodName == name && it.methodOwner.internalName == owner }.count() > 0
    }

    fun accessesField(opcode: Int, field: FieldWrapper): Boolean {
        return this.instructions.filter { it -> it.opcode == opcode && it.fieldName == field.name && it.fieldOwner.internalName == field.klass.name }.count() > 0
    }

    fun accessesField(opcode: Int, name:String, owner: String): Boolean {
        return this.instructions.filter { it -> it.opcode == opcode && it.fieldName == name && it.fieldOwner.internalName == owner }.count() > 0
    }
}