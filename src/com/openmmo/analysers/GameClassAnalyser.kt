package com.openmmo.analysers

import com.openmmo.ASMExtensions.isAbstract
import org.objectweb.asm.tree.ClassNode

class GameClassAnalyser : Analyser() {
    override var readableName = "GameClass"

    override fun canRun(node: ClassNode): Boolean {
        var fieldsSize = node.fields.count()
        var fieldsContainsAtomicBoolean = false;

        node.fields.forEach {
            if (it.desc.contains("AtomicBoolean")) fieldsContainsAtomicBoolean = true;
        }

        return fieldsSize > 30 && node.isAbstract() && fieldsContainsAtomicBoolean;
    }

    override fun analyse(node: ClassNode) {

    }
}