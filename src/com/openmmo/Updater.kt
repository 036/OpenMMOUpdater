package com.openmmo;

import com.google.gson.GsonBuilder
import com.openmmo.analysers.*
import com.openmmo.deobfuscator.Common.writeClasses
import com.openmmo.deobfuscator.Deobuscator
import com.openmmo.mapper.*
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Type
import java.io.FileWriter
import java.nio.file.Paths
import java.util.jar.JarFile

class Updater {
    private val pathToJar = Paths.get("C:\\Projects\\Reversing\\JVMReversing\\PokeMMO\\09062022 Update\\PokeMMO_new.jar")
    private val hooksJson = "C:\\PokeMMO\\Hooks.json"

    companion object {
        const val DEBUG = false
    }

    fun run() {
//        getHooks()
//        deobfuscate()
        Deobuscator().run(pathToJar, Paths.get("C:\\PokeMMO\\deob.jar"))
    }

    private fun getHooks() : List<IdClass> {
        val context = Mapper.Context()
        val pokeMMOClassAnalyser = PokeMMOClass::class.java

        val jarMapper = JarMapper(pokeMMOClassAnalyser.`package`.name, pokeMMOClassAnalyser.classLoader)
        val mappers = jarMapper.getOrderedMappers()

        //Performs analysis against the JAR using the mappings
        jarMapper.startAnalysing(mappers, pathToJar, context)

        val idClasses = context.buildIdHierarchy()

        if (!DEBUG) {
            println("Writing hooks to json")
            FileWriter(hooksJson).use { writer ->
                val gson = GsonBuilder().setPrettyPrinting().create()
                gson.toJson(idClasses, writer)
            }
        }

        printResults(idClasses)

        return idClasses
    }



    private fun printResults(identifiedClasses: List<IdClass>) {
        identifiedClasses.forEach {
            println("Found ${it.`class`} at ${it.name}")
        }

        var classes = 0
        var fields = 0
        var methods = 0

        identifiedClasses.forEach { idClass ->
            classes++
            idClass.fields.forEach { fields++ }
            idClass.methods.forEach { methods++ }
        }
        println("Found ${classes} classes ${fields} fields and ${methods} methods")
    }

    private fun Mapper.Context.buildIdHierarchyAll(): List<IdClass> {
        val identified = buildIdHierarchy().toMutableList()
        var i = 0
        val jar = classes.values.first().jar
        jar.classes.iterator().forEachRemaining { c ->
            if (classes.containsValue(c)) {
                c.instanceFields.filter { !fields.containsValue(it) }.forEach { f ->
                    if (!isTypeDenotable(f.type, classes.values)) return@forEach
                    val klass = identified.first { it.name == f.klass.name }
                    identified.remove(klass)
                    identified.add(klass.copy(fields = klass.fields.plus(IdField("__${f.name}", f.klass.name, f.name, f.access, f.desc))))
                }
                c.instanceMethods.filter { !methods.containsValue(it) }.iterator().forEach { m ->
                    if (!isTypeDenotable(m.returnType, classes.values)) return@forEach
                    if (m.arguments.any { !isTypeDenotable(it, classes.values) }) return@forEach
                    val klass = identified.first { it.name == m.klass.name }
                    identified.remove(klass)
                    val ps = m.arguments.mapIndexed { i, _ -> "arg$i" }
                    identified.add(klass.copy(methods = klass.methods.plus(IdMethod("__${m.name}_${i++}", m.klass.name, m.name, m.access, ps, m.desc))))
                }
            }
            c.staticFields.filter { !fields.containsValue(it) }.forEach { f ->
                if (!isTypeDenotable(f.type, classes.values)) return@forEach
                val klass = identified.first { it.name == "client" }
                identified.remove(klass)
                identified.add(klass.copy(fields = klass.fields.plus(IdField("__${f.klass.name}_${f.name}", f.klass.name, f.name, f.access, f.desc))))
            }
        }
        return identified
    }

    private fun isTypeDenotable(t: Type, classes: Collection<ClassWrapper>): Boolean {
        return when {
            t.isPrimitive -> true
            t.sort == Type.ARRAY -> isTypeDenotable(t.elementType, classes)
            else -> classes.any { it.name == t.internalName } || try { Class.forName(t.className); true } catch (e: Exception) { false }
        }
    }
}

fun main() {
    println("Starting updater")
    Updater().run()
}