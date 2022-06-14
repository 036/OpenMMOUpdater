package com.openmmo.analysers

import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import java.lang.reflect.Modifier

class PokeMMOClassAnalyser : Analyser() {
    override var readableName = "PokeMMOClass"

    override fun canRun(node: ClassNode): Boolean {
        if (!Modifier.isAbstract(node.access) && node.interfaces.any()) return false;

        var fields = 0;
        var nonStaticFieldsCount = 0;
        var hasInputMultiplexer = false;

        node.fields.forEach {
            if (it.desc.contains("Multiplexer")) {
                hasInputMultiplexer = true;
            }

            val isStatic = (it.access != 0 && Opcodes.ACC_STATIC != 0)
            if (isStatic) fields++
            if (!isStatic) nonStaticFieldsCount++
        }

        return (fields > 24 && node.methods.count() > 7 && nonStaticFieldsCount == 0 && hasInputMultiplexer)
    }

    override fun analyse(node: ClassNode) {

    }
}