package com.openmmo.analysers

import com.openmmo.ASMExtensions.isAbstract
import com.openmmo.ASMExtensions.isConstructor
import org.objectweb.asm.tree.ClassNode

class MapDataAnalyser : Analyser() {
    override var readableName = "MapData"

    override fun canRun(node: ClassNode): Boolean {
        if (!node.isAbstract() && !node.interfaces.contains("com/badlogic/gdx/utils/Disposable")) return false;


        var constructorMatches = false;
        node.methods.forEach {
            if (it.isConstructor()) {
                if (it.desc.equals("(BBBB)V")) {
                    constructorMatches = true;
                }
            }
        }

        return constructorMatches;
    }

    override fun analyse(node: ClassNode) {

    }
}