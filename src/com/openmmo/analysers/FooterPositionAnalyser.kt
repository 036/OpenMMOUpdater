package com.openmmo.analysers

import com.openmmo.ASMExtensions.isConstructor
import org.objectweb.asm.tree.ClassNode
import java.lang.reflect.Modifier

class FooterPositionAnalyser : Analyser() {
    override var readableName = "FooterPosition"

    override fun canRun(node: ClassNode): Boolean {
        if (!Modifier.isAbstract(node.access) && node.interfaces.any()) return false;

        var hasArrayField = false;
        var hasMatchedConstructor = false;

        node.fields.forEach {
            if (it.desc.contains("Array")) hasArrayField = true
        }

        node.methods.forEach {
            if (it.isConstructor()) {
                if (it.desc.contains("(SS)V")) {
                    hasMatchedConstructor = true;
                }
            }
        }
        return hasArrayField && hasMatchedConstructor;
    }

    override fun analyse(node: ClassNode) {

    }
}