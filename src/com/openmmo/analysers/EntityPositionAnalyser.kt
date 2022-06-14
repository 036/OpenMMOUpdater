package com.openmmo.analysers

import com.openmmo.Updater
import org.objectweb.asm.tree.ClassNode


class EntityPositionAnalyser : Analyser() {
    override var readableName = "EntityPosition"

    override fun canRun(node: ClassNode): Boolean {
        node.methods.forEach {
            if (it.name.equals("<init>")) {
                if (it.desc.equals("(BBBZSSBB)V")) {
                    return true;
                }
            }
        }

        return false;
    }

    override fun analyse(node: ClassNode) {
        Updater.analysedClasses[readableName] = node
    }
}