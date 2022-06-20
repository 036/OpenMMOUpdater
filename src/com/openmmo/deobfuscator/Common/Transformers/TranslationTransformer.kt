package com.openmmo.deobfuscator.Common.Transformers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.openmmo.deobfuscator.Common.Transformer
import com.openmmo.mapper.IdClass
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.*
import java.nio.file.Files
import java.nio.file.Path

class TranslationTransformer(hooks: Path) : Transformer.Single() {
    val mappedClasses = Gson().fromJson<List<IdClass>>(Files.newBufferedReader(hooks), object : TypeToken<List<IdClass?>?>() {}.getType())
    val translationHelper = mappedClasses.filter { it.`class` == "TranslationHelper"}.first()

    override fun transform(klass: ClassNode) {
        findTranslationHelperCall(klass)
    }

    private fun findTranslationHelperCall(klass: ClassNode) {
        klass.methods.forEach { method ->
            method.instructions.forEach { inst ->
                if (inst.opcode == Opcodes.INVOKESTATIC) {
                    if (inst is MethodInsnNode && inst.owner.contains(translationHelper.name)) {
                        val previousInstruction = inst.previous
                        if (previousInstruction is IntInsnNode) {
                            val intCode = previousInstruction.operand
                            println("Doing at ${klass.name}")
                            method.instructions.insertBefore(inst, InsnNode(Opcodes.POP))
                            method.instructions.insertBefore(inst, LdcInsnNode("blah"))
                            method.instructions.remove(inst)
                        }
                    }
                }
            }
        }
    }
}