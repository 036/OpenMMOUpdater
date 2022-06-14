package com.openmmo.analysers

import com.openmmo.ASMExtensions.hasInterfaces
import com.openmmo.ASMExtensions.isAbstract
import com.openmmo.ASMExtensions.isConstructor
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LdcInsnNode

class GameServerClassAnalyser : Analyser() {
    override var readableName = "GameServer"

    override fun canRun(node: ClassNode): Boolean {
        if (node.isAbstract() && node.hasInterfaces()) return false;

        var hasDequeField = false;
        var hasInflaterField = false;
        var hasScheduledFutureClass = false;
        var hasSocketChannelInConstructor = false;
        var foundMessage = false;

        node.fields.forEach {
            if (it.desc.contains("Ljava/util/ArrayDeque")) hasDequeField = true;
            if (it.desc.contains("Ljava/util/zip/Inflater;")) hasInflaterField = true;
            if (it.desc.contains("Ljava/util/concurrent/ScheduledFuture;")) hasScheduledFutureClass = true;
        }

        node.methods.forEach { it ->
            if (it.isConstructor()) {
                if (it.desc.contains("Ljava/nio/channels/SocketChannel")) hasSocketChannelInConstructor = true;
            }

            it.instructions.forEach {
                run {
                    if (it is LdcInsnNode) {
                        if (it.cst.equals("error handling gs message ")) foundMessage = true;
                    }
                }
            }
        }

        return hasDequeField && hasInflaterField && hasScheduledFutureClass && hasSocketChannelInConstructor && foundMessage
    }

    override fun analyse(node: ClassNode) {

    }
}