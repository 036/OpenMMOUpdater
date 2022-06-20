package com.openmmo.deobfuscator.Common.Models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText

@JacksonXmlRootElement(namespace = "strings")
data class StringData(

    @JacksonXmlProperty(isAttribute = false, localName = "string")
    @JacksonXmlElementWrapper()
    val translationStrings: List<TranslationString>? = null
)


data class TranslationString(
    @JacksonXmlProperty(isAttribute = true)
    val id: Int? = null,
    @JacksonXmlText
    val value: String = ""
)


