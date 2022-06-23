package com.openmmo.deobfuscator.Common.Nodes

class ClassNodeManager {

    val classMap = HashMap<String, ClassNode>()

    var classCounter = 0

    fun addClass(originalName: String): String {
        if(isMapped(originalName))
            return classMap[originalName]?.newName ?: ""

        val newName = originalName.substringBeforeLast("/") + "/class_$classCounter"
        classMap[originalName] = ClassNode(originalName, newName)
        classCounter++
        return newName
    }

    fun getClass(originalName: String): ClassNode {
        if (!isMapped(originalName)) {
            addClass(originalName)
        }
        return classMap[originalName]!!
    }

    fun isMapped(originalName: String): Boolean {
        return classMap.containsKey(originalName)
    }

}