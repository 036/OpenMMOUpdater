package com.openmmo.deobfuscator.Common.Nodes

class ClassNode(val originalName: String, val newName: String) {

    private val fieldMap = HashMap<String, FieldNode>()
    private val methodMap = HashMap<String, MethodNode>()

    private var fieldCounter = 0
    private var methodCounter = 0


    fun addField(originalName: String): String {
        if(isFieldMapped(originalName))
            return fieldMap[originalName]?.newName ?: ""

        if(!isNameObfuscated(originalName)) {
            fieldMap[originalName] = FieldNode(originalName, originalName)
            return originalName
        }

        val newName = "field_$fieldCounter"

        fieldMap[originalName] = FieldNode(originalName, newName)
        fieldCounter++

        return newName
    }

    // TODO: add better way
    private val obfuscationPatterns = arrayOf<Regex>( "(?i)^PRN\\d?$".toRegex(), "(?i)^AUX\\d?$".toRegex(), "(?i)^COM\\d$".toRegex(), "(?i)^CON$".toRegex(), "(?i)^[A-Z]{1,2}\\d?$".toRegex(), "(?i)^[A-Z]\\d{1,2}$".toRegex(), "(?i)^LPT\\d$".toRegex() )
    fun isNameObfuscated(name: String): Boolean {
        //return name != "<init>" && name != "<clinit>" && name != "valueOf" && name != "values" && name != "toString"
        //        && name != "equals" && name != "hashCode" && name != "clone" && name.length <= 4;

        for (regex in obfuscationPatterns) {
            if(regex.matches(name))
                return true
        }

        return name != "<init>" && name != "<clinit>" && name.length > 6
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