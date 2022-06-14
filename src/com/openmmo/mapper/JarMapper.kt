package com.openmmo.mapper

import com.google.common.reflect.ClassPath
import com.openmmo.analysers.Analyser
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

class JarMapper(vararg val classMappers: KClass<out Analyser>) {

    @Suppress("UNCHECKED_CAST")
    constructor(pkg: String, classLoader: ClassLoader) : this(
        *ClassPath.from(classLoader)
            .getTopLevelClassesRecursive(pkg)
            .mapNotNull { it.load().kotlin }
            .filter { it.isSubclassOf(Analyser::class) }
            .map { it as KClass<out Analyser> }
            .toTypedArray()
    )

}