package com.openmmo.mapper

import java.lang.reflect.Modifier
import kotlin.reflect.KClass

abstract class IdentityMapper<T> : Mapper<T>() {

    /** Runs the predicate on the IdentityMapper type **/
    override fun match(jar: JarWrapper): T {
        try {
            val matches = options(jar).filter { predicate(it) }
            if (matches.count() > 1) {
                println("[WARN] Found ${matches.count()} matches for analyser ${this.javaClass.simpleName}")
                matches.toList().forEach { println("${this.javaClass.simpleName} analyser matched in ${it.toString()}") }
                throw Exception()
            } else {
                return matches.single()
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    protected abstract fun options(jar: JarWrapper): Sequence<T>

    protected abstract val predicate: Predicate<T>

    abstract class Class : IdentityMapper<ClassWrapper>(), ElementMatcher.Class {
        override fun options(jar: JarWrapper): Sequence<ClassWrapper> {
            return jar.classes.asSequence()
        }
    }

    abstract class Enum(vararg searchStrings: String) : IdentityMapper<ClassWrapper>(), ElementMatcher.Class {
        override fun options(jar: JarWrapper): Sequence<ClassWrapper> {
            return jar.classes.asSequence()
        }

        override val predicate = predicateOf<ClassWrapper> { it.superType.className.contains("Enum") }
            .and {
                val amountOfStrings = searchStrings.size
                var matchedCount = 0
                searchStrings.forEach { searchString ->
                    if (it.methods.any { it.instructionsContainsString(searchString) }) matchedCount++
                }

                matchedCount == amountOfStrings
            }
    }

    abstract class Field : IdentityMapper<FieldWrapper>(), ElementMatcher.Field {
        override fun options(jar: JarWrapper): Sequence<FieldWrapper> {
            return jar.classes.asSequence().flatMap { it.fields.asSequence() }
        }
    }

    abstract class Method : IdentityMapper<MethodWrapper>(), ElementMatcher.Method {
        override fun options(jar: JarWrapper): Sequence<MethodWrapper> {
            return jar.classes.asSequence().flatMap { it.methods.asSequence() }
        }
    }

    abstract class StaticField : Field() {
        override fun options(jar: JarWrapper): Sequence<FieldWrapper> {
            return super.options(jar).filter { Modifier.isStatic(it.access) }
        }
    }

    abstract class InstanceField() : Field() {

        @Suppress("UNCHECKED_CAST")
        private val enclosing = javaClass.enclosingClass.kotlin as KClass<out Mapper<ClassWrapper>>

        override fun options(jar: JarWrapper): Sequence<FieldWrapper> {
            return super.options(jar).filter { context.classes[enclosing] == it.klass && !Modifier.isStatic(it.access)}
        }
    }

    abstract class StaticMethod() : Method() {
        override fun options(jar: JarWrapper): Sequence<MethodWrapper> {
            return super.options(jar).filter { Modifier.isStatic(it.access) && !it.isClassInitializer }
        }
    }

    abstract class InstanceMethod() : Method() {

        @Suppress("UNCHECKED_CAST")
        private val enclosing = javaClass.enclosingClass.kotlin as KClass<out Mapper<ClassWrapper>>

        override fun options(jar: JarWrapper): Sequence<MethodWrapper> {
            return super.options(jar).filter { context.classes[enclosing] == it.klass && !Modifier.isStatic(it.access) && !it.isConstructor }
        }
    }
}