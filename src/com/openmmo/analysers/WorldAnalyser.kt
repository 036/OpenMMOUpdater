package com.openmmo.analysers

import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode

class WorldAnalyser : Analyser() {
    override var readableName = "World"

    override fun canRun(node: ClassNode): Boolean {
        if (!node.interfaces.containsAll(listOf("java/lang/Iterable","com/badlogic/gdx/utils/Disposable" ))) return false;

        node.fields.forEach { if (it.desc.equals("Ljava/util/concurrent/ConcurrentHashMap;") && (it.access != 0 && Opcodes.ACC_STATIC != 0)) return true }

        return false;
    }

    override fun analyse(node: ClassNode) {

    }
}