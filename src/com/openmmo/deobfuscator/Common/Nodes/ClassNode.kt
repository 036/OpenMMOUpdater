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

    // TODO: add better way
    fun isNameObfuscated(name: String): Boolean {
        //return name != "<init>" && name != "<clinit>" && name != "valueOf" && name != "values" && name != "toString"
        //        && name != "equals" && name != "hashCode" && name != "clone" && name.length <= 4;
        return name != "<init>" && name != "<clinit>" && name.length < 5
    }

    fun addMethod(originalName: String, desc: String): String {
        if(isMethodMapped(originalName, desc)) {
            return methodMap[originalName + desc]!!.newName
        }

        if(!isNameObfuscated(originalName)) {
            methodMap[originalName + desc] = MethodNode(originalName, originalName, desc)
            return originalName
        }

        val newName = "method_$methodCounter"
        methodMap[originalName + desc] = MethodNode(originalName, newName, desc)
        methodCounter++
        return newName
    }

    fun isFieldMapped(originalName: String): Boolean {
        return fieldMap.containsKey(originalName)
    }

    fun isMethodMapped(originalName: String, desc: String): Boolean {
        return methodMap.containsKey(originalName + desc)
    }

}