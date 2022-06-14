package com.openmmo.analysers

import org.objectweb.asm.tree.ClassNode

class GameEntryAppListenerAnalyser : Analyser() {
    override var readableName = "GameEntryAppListener"

    override fun canRun(node: ClassNode): Boolean {
        var hasPauseMethod = false;
        var hasCreateMethod = false;
        var hasFieldOfTypeDynamicImage = false;
        node.methods.forEach {
            if (it.name.equals("pause")) hasPauseMethod = true
            if (it.name.equals("create")) hasCreateMethod = true
        }

        node.fields.forEach {
            if (it.desc.contains("TWLDynamicImage")) hasFieldOfTypeDynamicImage = true
        }

        return hasPauseMethod && hasCreateMethod && hasFieldOfTypeDynamicImage
    }

    override fun analyse(node: ClassNode) {

    }
}