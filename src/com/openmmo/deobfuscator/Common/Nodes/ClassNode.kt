package com.openmmo.deobfuscator.Common.Nodes

class ClassNode(val originalName: String, val newName: String) {

    private val fieldMap = HashMap<String, FieldNode>()
    private val methodMap = HashMap<String, MethodNode>()

    private var fieldCounter = 0
    private var methodCounter = 0


    fun addField(originalName: String): String {
        if(isFieldMapped(originalName))
            return fieldMap[originalName]?.newName ?: ""

        val newName = "field_$fieldCounter"

        fieldMap[originalName] = FieldNode(originalName, newName)
        fieldCounter++

        return newName
    }

    fun addMethod(originalName: String, desc: String): String {
        if(isMethodMapped(originalName, desc))
            return methodMap[originalName + desc]!!.newName

        if(originalName == "<init>" || originalName == "<clinit>") {
            methodMap[originalName + desc] = MethodNode(originalName, originalName, desc)
        }

        val newName = "method_$fieldCounter"

        methodMap[originalName + desc] = MethodNode(originalName, newName, desc)
        methodCounter++

        return newName
    }

    fun isFieldMapped(originalName: String): Boolean {
        return fieldMap.keys.any { originalName == it }
    }

    fun isMethodMapped(originalName: String, desc: String): Boolean {
        return methodMap.keys.any { originalName + desc == it }
    }

}