package com.openmmo.mapper

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import org.objectweb.asm.Type
import java.lang.Exception
import kotlin.reflect.KClass

abstract class Mapper<T> : ElementMatcher<T> {

    lateinit var context: Context

    inline fun <reified T: Mapper<ClassWrapper>> klass(): ClassWrapper {
        return context.classes.getValue(T::class)
    }

    inline fun <reified T: Mapper<ClassWrapper>> type(): Type {
        return klass<T>().type
    }

    inline fun <reified T: Mapper<FieldWrapper>> field(): FieldWrapper {
        return context.fields.getValue(T::class)
    }

    inline fun <reified T: Mapper<MethodWrapper>> method(): MethodWrapper {
        return context.methods.getValue(T::class)
    }

    fun analyse(jar: JarWrapper) {
        val t: T
        try {
            t = match(jar)
        } catch (e: Exception ) {
            println("Unable to find a class for ${this::class}")
            throw e
        }

        val klass = this::class
        @Suppress("UNCHECKED_CAST")
        when (this) {
            is ElementMatcher.Class -> {
                klass as KClass<out Mapper<ClassWrapper>>
                t as ClassWrapper
                check(!context.classes.inverse().containsKey(t))
                context.classes[klass] = t
            }
            is ElementMatcher.Field -> {
                klass as KClass<out Mapper<FieldWrapper>>
                t as FieldWrapper
                check(!context.fields.inverse().containsKey(t))
                context.fields[klass] = t
            }
            is ElementMatcher.Method -> {
                klass as KClass<out Mapper<MethodWrapper>>
                t as MethodWrapper
                check(!context.methods.inverse().containsKey(t))
                context.methods[klass] = t
            }
            else -> error(this)
        }
    }

    class Context {

        val classes: BiMap<KClass<out Mapper<ClassWrapper>>, ClassWrapper> = HashBiMap.create()

        val fields: BiMap<KClass<out Mapper<FieldWrapper>>, FieldWrapper> = HashBiMap.create()

        val methods: BiMap<KClass<out Mapper<MethodWrapper>>, MethodWrapper> = HashBiMap.create()
    }
}