package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import java.lang.reflect.Modifier

class ProcessService : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { Modifier.isAbstract(it.access) }
        .and { it.interfaces.isEmpty() }
        .and { it.methods.any { it.instructions.filter { ins -> ins.opcode == Opcodes.INVOKESTATIC }.any { it.methodName == "allProcesses" } } }

    @DependsOn(ProcessService::class)
    class getClasses : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.klass.type == type<ProcessService>() }
            .and { it.instructions.filter { ins -> ins.opcode == Opcodes.INVOKESTATIC }.any { it.methodName.contains("allProcesses") } }
    }

    @DependsOn(ProcessService::class)
    class pathToProcessName : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.klass.type == type<ProcessService>() }
            .and { it.arguments.size == 1 }
    }
}