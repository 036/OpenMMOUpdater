package com.openmmo.analysers

import com.openmmo.Updater
import org.objectweb.asm.tree.ClassNode

abstract class Analyser {
    abstract var readableName: String

    fun run(node: ClassNode) {
        if (this.canRun(node)) {
            analyse(node)
            if (Updater.DEBUG) {
                logSuccess(node)
            }
            Updater.analysedClasses.put(readableName, node)
        }
    }

    fun logSuccess(node: ClassNode) {
        println("Successfully found $readableName in ${node.name} ")
    }

    abstract fun canRun(node: ClassNode) : Boolean
    abstract fun analyse(node: ClassNode)
}