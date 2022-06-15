package com.openmmo;

import com.google.common.base.Stopwatch
import com.openmmo.analysers.*
import com.openmmo.mapper.*
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

class Updater {
    companion object {
        const val DEBUG = false;
        var analysedClasses: HashMap<String, ClassNode> = HashMap()
        var unanalysedClasses: HashMap<String, ClassNode> = HashMap()
    }

//    var analysers: MutableList<Analyser> = mutableListOf()

    fun addAnalysers() {
//        analysers.add(GameEntryAppListenerAnalyser())
//        analysers.add(EntityPosition())
//        analysers.add(FooterPosition())
//        analysers.add(MapDataAnalyser())
//        analysers.add(ShusDebugMenuAnalyser())
//        analysers.add(GameServerClassAnalyser())
//        analysers.add(BreedWindowClassAnalyser())
//        analysers.add(PokemonClassAnalyser())
//        analysers.add(TimelineTweenBaseAnalyser())
//        analysers.add(TimelineTweenChildAnalyser())
    }


    private fun runAnalysers() {
//        println("${unanalysedClasses.values.size} PokeMMO classes found")
//        for (classNode: ClassNode in unanalysedClasses.values) {
//            for (analyser: Analyser in analysers) {
//                analyser.run(classNode)
//            }
//        }
    }

    fun run() {
//        addAnalysers()
//        val jarFile = JarFile("C:\\Projects\\Reversing\\JVM Reversing\\PokeMMO\\09062022 Update\\PokeMMO_new.jar")
//        unanalysedClasses = parseJar(jarFile)
//        runAnalysers()
        val context = Mapper.Context()
        val pokeMMOClassAnalyser = PokeMMOClassMapper::class.java
        val jarMapperStopwatch = Stopwatch.createStarted()

        JarMapper(pokeMMOClassAnalyser.`package`.name, pokeMMOClassAnalyser.classLoader)
            .map(Paths.get("C:\\Projects\\Reversing\\JVMReversing\\PokeMMO\\09062022 Update\\PokeMMO_new.jar"), context)

        jarMapperStopwatch.stop()
        println("Jar mapping took ${jarMapperStopwatch.elapsed(TimeUnit.MILLISECONDS)}ms")
        val stopwatch = Stopwatch.createStarted();

        val idClasses = context.buildIdHierarchy()
        idClasses.forEach {
            println("Found ${it.`class`} at ${it.name}")
        }
        stopwatch.stop()
        var classes = 0
        var fields = 0
        var methods = 0

        idClasses.forEach { idClass ->
            classes++
            idClass.fields.forEach { fields++ }
            idClass.methods.forEach { methods++ }
        }

        println("Found ${classes} classes ${fields} fields and ${methods} methods in ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}ms")
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