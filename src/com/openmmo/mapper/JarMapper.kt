package com.openmmo.mapper

import com.google.common.reflect.ClassPath
import java.nio.file.Path
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

class JarMapper(vararg val classMappers: KClass<out Mapper<ClassWrapper>>) {


    /**
     * Gets all analysers that extend the mapper base class
     */
    @Suppress("UNCHECKED_CAST")
    constructor(pkg: String, classLoader: ClassLoader) : this(
        *ClassPath.from(classLoader)
            .getTopLevelClassesRecursive(pkg)
            .mapNotNull { it.load().kotlin }
            .filter { it.isSubclassOf(Mapper::class) }
            .map { it as KClass<out Mapper<ClassWrapper>> }
            .toTypedArray()
    )

    /**
     * Gets all classmappers and nested classes of classmappers
     * Then orders the dependencies
     */
    fun map(jar: Path, context: Mapper.Context) {
        val jar2 = JarWrapper(jar)
        @Suppress("UNCHECKED_CAST")
        val unordered = classMappers.asSequence()
            .flatMap { it.nestedClasses.asSequence().plus(it) }
            .filter { it.isSubclassOf(Mapper::class) }
            .map { it as KClass<out Mapper<*>> }
        orderDependencies(unordered).map { it.createInstance() }.forEach { mapper ->
            mapper.context = context
            mapper.map(jar2)
        }
    }

    private fun orderDependencies(unorderedClasses: Sequence<KClass<out Mapper<*>>>): Sequence<KClass<out Mapper<*>>> {
        val ordered = LinkedHashSet<KClass<out Mapper<*>>>()
        val unorderedCollection = unorderedClasses.toHashSet()

        while (unorderedCollection.isNotEmpty()) {
            val startSize = unorderedCollection.size
            val iterator = unorderedCollection.iterator()

            while (iterator.hasNext()) {
                val o = iterator.next()
                @Suppress("UNCHECKED_CAST")
                val enclosing = o.java.enclosingClass?.kotlin as KClass<out Mapper<*>>?
                val enclosingFound = enclosing == null || enclosing in ordered
                val dependencies = o.java.getAnnotation(DependsOn::class.java)?.mappers ?: emptyArray()
                val dependenciesFound = dependencies.all { it in ordered }
                if (enclosingFound && dependenciesFound) {
                    iterator.remove()
                    ordered.add(o)
                }
            }

            check(startSize != unorderedCollection.size)
        }

        return ordered.asSequence()
    }
}