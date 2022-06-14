package com.openmmo.analysers

import com.openmmo.ASMExtensions.isConstructor
import org.objectweb.asm.tree.ClassNode

class BreedWindowClassAnalyser : Analyser() {
    override var readableName = "BreedWindow"

    val expectedLabels = 6;
    val expectedInterfaces = 1;
    val expectedButtons = 2;

    override fun canRun(node: ClassNode): Boolean {
        var matchedConstructor = false
        var buttonFields = 0
        val interfacesCount = node.interfaces.count()
        var dialogLayoutCount = 0;
        var labelCount = 0;

        node.methods.forEach {
            if (it.isConstructor() && it.desc.contains("(B)V")) matchedConstructor = true
        }

        node.fields.forEach {
            if (it.desc.contains("Button")) buttonFields++
            if (it.desc.contains("DialogLayout")) dialogLayoutCount++;
            if (it.desc.contains("Label")) labelCount++;
        }

        return interfacesCount == expectedInterfaces
                && buttonFields == expectedButtons
                && matchedConstructor
                && labelCount == expectedLabels;
    }

    override fun analyse(node: ClassNode) {

    }
}