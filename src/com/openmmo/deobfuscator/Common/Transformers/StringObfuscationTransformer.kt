package com.openmmo.deobfuscator.Common.Transformers

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.openmmo.Updater
import com.openmmo.deobfuscator.Common.Models.StringData
import com.openmmo.deobfuscator.Common.Transformer
import com.openmmo.mapper.IdClass
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class StringObfuscationTransformer(hooks: Path) : Transformer.Single() {
    val mappedClasses = Gson().fromJson<List<IdClass>>(Files.newBufferedReader(hooks), object : TypeToken<List<IdClass?>?>() {}.type)

    override fun transform(klass: ClassNode) {
        klass.methods.forEach { m ->
            // check if method contains INVOKESPECIAL java/lang/String.<init>([B)V
            if(m.instructions.any { it.opcode == Opcodes.INVOKESPECIAL && it is MethodInsnNode && invokesMethod(it, "java/lang/String", "<init>", "([B)V") }) {

                println("Found possible String obfuscation Class: ${klass.name} Method: ${m.name}")
                // Walk instructions back up
                val reversed = m.instructions.reversed()
                var StringInitIns: MethodInsnNode? = null;
                var byteList = ArrayList<Byte>()
                var foundStringConstructor = false
                var foundBASTORE = false

                reversed.forEach { ins ->
                    if(!foundStringConstructor && ins.opcode == Opcodes.INVOKESPECIAL && ins is MethodInsnNode &&
                        invokesMethod(ins, "java/lang/String", "<init>", "([B)V")) {
                        foundStringConstructor = true
                        StringInitIns = ins;
                    }
                    else if(foundStringConstructor) {

                        if(ins.opcode == Opcodes.NEWARRAY) {
                            // previous bc this isn't reversed
                            val nextIns = ins.previous;

                            // checking for valid previous instruction
                            if((nextIns.opcode == Opcodes.BIPUSH && nextIns is IntInsnNode && nextIns.operand == byteList.size) ||
                                (nextIns.opcode == Opcodes.ICONST_5 && 5 == byteList.size) ||
                                (nextIns.opcode == Opcodes.ICONST_4 && 4 == byteList.size) ||
                                (nextIns.opcode == Opcodes.ICONST_3 && 3 == byteList.size) ||
                                (nextIns.opcode == Opcodes.ICONST_2 && 2 == byteList.size) ||
                                (nextIns.opcode == Opcodes.ICONST_1 && 1 == byteList.size) ||
                                (nextIns.opcode == Opcodes.ICONST_0 && 0 == byteList.size)) {

                                val str = String(byteList.reversed().toByteArray());
                                println("Found String $str Class: ${klass.name} Method: ${m.name}")
                                m.instructions.insert(StringInitIns, LdcInsnNode(str))
                                m.instructions.insert(StringInitIns, InsnNode(Opcodes.POP))

                                // TODO: Fix crash or implement better way
                                // cleaning bytecode
                                /*var thisIns: AbstractInsnNode = StringInitIns!!;
                                var prevIns: AbstractInsnNode = thisIns.previous;
                                 do {
                                    m.instructions.remove(thisIns)
                                    thisIns = prevIns;
                                    prevIns = thisIns.previous
                                } while (thisIns.opcode != Opcodes.NEWARRAY)
                                m.instructions.remove(thisIns)*/

                            } else {
                                // Array size could not match or previous instruction wasn't valid
                                println("Error validating ByteArray Class: ${klass.name} Method: ${m.name}")
                            }

                            // resetting state
                            StringInitIns = null;
                            byteList = ArrayList<Byte>()
                            foundStringConstructor = false;
                            foundBASTORE = false
                        }

                        if(foundBASTORE && ins.opcode == Opcodes.BIPUSH && ins is IntInsnNode) {
                            byteList.add(ins.operand.toByte())
                            // println("Found Byte ${ins.operand.toByte()} Class: ${klass.name} Method: ${m.name}")
                            foundBASTORE = false
                        }

                        if(ins.opcode == Opcodes.BASTORE)
                            foundBASTORE = true
                    }
                }
            }
        }
    }

    fun invokesMethod(ins: MethodInsnNode, owner: String, name: String, desc: String): Boolean {
        return ins.owner == owner && ins.name == name && ins.desc == desc;
    }
}