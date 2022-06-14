package com.openmmo.analysers

import com.openmmo.Updater
import org.objectweb.asm.tree.ClassNode

class TimelineTweenChildAnalyser : Analyser() {
    override var readableName = "TimelineTweenChild"

    override fun canRun(node: ClassNode): Boolean {
        val baseTimeline = Updater.analysedClasses["TimelineTweenBase"]

        return node.superName.equals(baseTimeline?.name)
    }

    override fun analyse(node: ClassNode) {

    }
}