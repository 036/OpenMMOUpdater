package com.openmmo.analysers

import com.openmmo.ASMExtensions.hasInterfaces
import com.openmmo.ASMExtensions.isAbstract
import org.objectweb.asm.tree.ClassNode

class TimelineTweenBaseAnalyser : Analyser() {
    override var readableName = "TimelineTweenBase"

    override fun canRun(node: ClassNode): Boolean {
        if (!node.isAbstract()) return false;

        var hasTimeline = false;
        var hasVector3 = false;
        node.fields.forEach { field ->
            if (field.desc.contains("Laurelienribon/tweenengine/Timeline")) hasTimeline = true;
            if (field.desc.contains("Vector3")) hasVector3 = true;
        }

        return hasTimeline && hasVector3 && !node.hasInterfaces();
    }

    override fun analyse(node: ClassNode) {

    }
}