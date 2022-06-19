package com.openmmo.deobfuscator.Common.Nodes

import com.openmmo.deobfuscator.Common.Nodes.ClassNode

class ClassNodeManager {

    val classMap = HashMap<String, ClassNode>()

    var classCounter = 0;

    fun addClass(originalName: String): String {
        if(isMapped(originalName))
            return classMap[originalName]?.newName ?: ""

        val newName = originalName.substringBeforeLast("/") + "/class_$classCounter"
        classMap[originalName] = ClassNode(originalName, newName)
        classCounter++
        return newName
    }

    fun getClass(originalName: String): ClassNode {
        if (classMap[originalName] == null) {
            addClass(originalName)
        }
        return classMap[originalName]!!
    }

    fun isMapped(originalName: String): Boolean {
        return classMap.keys.any { originalName == it }
    }

}