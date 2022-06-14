package com.openmmo;

import com.openmmo.analysers.*
import com.openmmo.mapper.JarMapper
import com.openmmo.utils.parseJar
import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import java.util.jar.JarFile

class Updater {
    companion object {
        const val DEBUG = false;
        var analysedClasses: HashMap<String, ClassNode> = HashMap()
        var unanalysedClasses: HashMap<String, ClassNode> = HashMap()
    }

    var analysers: MutableList<Analyser> = mutableListOf()

    fun addAnalysers() {
        analysers.add(PokeMMOClassAnalyser())
        analysers.add(GameEntryAppListenerAnalyser())
        analysers.add(EntityPositionAnalyser())
        analysers.add(FooterPositionAnalyser())
        analysers.add(WorldAnalyser())
        analysers.add(BattleClassAnalyser())
        analysers.add(MapDataAnalyser())
        analysers.add(ShusDebugMenuAnalyser())
        analysers.add(GameClassAnalyser())
        analysers.add(GameServerClassAnalyser())
        analysers.add(BreedWindowClassAnalyser())
        analysers.add(PokemonClassAnalyser())
        analysers.add(TimelineTweenBaseAnalyser())
        analysers.add(TimelineTweenChildAnalyser())
    }


    private fun runAnalysers() {
        println("${unanalysedClasses.values.size} PokeMMO classes found")
        for (classNode: ClassNode in unanalysedClasses.values) {
            for (analyser: Analyser in analysers) {
                analyser.run(classNode)
            }
        }
    }

    fun run() {
//        addAnalysers()
//        val jarFile = JarFile("C:\\Projects\\Reversing\\JVM Reversing\\PokeMMO\\09062022 Update\\PokeMMO_new.jar")
//        unanalysedClasses = parseJar(jarFile)
//        runAnalysers()
        val pokeMMOClassAnalyser = PokeMMOClassAnalyser::class.java

        val jarMapper = JarMapper(pokeMMOClassAnalyser.`package`.name, pokeMMOClassAnalyser.classLoader)

    }



}

fun main() {
    println("Starting updater")
    Updater().run()
}