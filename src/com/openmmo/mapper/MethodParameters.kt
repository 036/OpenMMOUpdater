package com.openmmo.mapper

import java.lang.annotation.Inherited

@Inherited
@Target(AnnotationTarget.CLASS)
annotation class MethodParameters(
    vararg val names: String = emptyArray()
)
