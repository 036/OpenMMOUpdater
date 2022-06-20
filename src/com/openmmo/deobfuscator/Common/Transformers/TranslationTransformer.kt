package com.openmmo.deobfuscator.Common.Transformers

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.openmmo.deobfuscator.Common.Models.StringData
import com.openmmo.deobfuscator.Common.Transformer
import com.openmmo.mapper.IdClass
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class TranslationTransformer(hooks: Path) : Transformer.Single() {
    val mappedClasses = Gson().fromJson<List<IdClass>>(Files.newBufferedReader(hooks), object : TypeToken<List<IdClass?>?>() {}.getType())
    val translationHelper = mappedClasses.filter { it.`class` == "TranslationHelper"}.first()
    val getStringByID = mappedClasses.first { it.`class` == "TranslationHelper" }.methods.first { it.method == "GetStringByID" }
    val mappedXmlStrings = getXmlStrings()

    override fun transform(klass: ClassNode) {
        findTranslationHelperCall(klass)
    }

    private fun getXmlStrings(): StringData? {
        val module = JacksonXmlModule()
        module.setDefaultUseWrapper(false);
        val xmlMapper = XmlMapper(module)
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        xmlMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
        return xmlMapper.readValue<StringData>(File("C:\\Program Files\\PokeMMO\\data\\strings\\strings_en.xml"), StringData::class.java)
    }

    private fun findTranslationHelperCall(klass: ClassNode) {
        klass.methods.forEach { method ->
            method.instructions.forEach { inst ->
                if (inst.opcode == Opcodes.INVOKESTATIC) {
                    if (inst is MethodInsnNode && inst.owner.contains(translationHelper.name) && inst.name.contains(getStringByID.name)) {
                        val previousInstruction = inst.previous
                        if (previousInstruction is IntInsnNode) {
                            val intCode = previousInstruction.operand
                            try {
                                if (mappedXmlStrings != null) {
                                    val result = mappedXmlStrings.translationStrings?.first { it.id == intCode }
                                    // TODO: add a way to read IntValue from SIPUSH
                                    if (result != null) {
                                        println("Replace TranslationHelper.GetStringByID(I) call in ${klass.name}")
                                        method.instructions.insertBefore(inst, InsnNode(Opcodes.POP))
                                        method.instructions.insertBefore(inst, LdcInsnNode(result.value))
                                        method.instructions.remove(inst)
                                    }
                                }
                            } catch (e: Exception) {

                            }
                        }
                    }
                }
            }
        }
    }


}