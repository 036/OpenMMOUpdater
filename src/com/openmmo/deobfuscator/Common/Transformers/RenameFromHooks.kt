package com.openmmo.deobfuscator.Common.Transformers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.openmmo.deobfuscator.Common.Nodes.ClassNodeManager
import com.openmmo.mapper.IdClass
import org.objectweb.asm.commons.Remapper
import java.nio.file.Files
import java.nio.file.Path

class RenameFromHooks(hooks: Path) : Remapper() {
    //TODO: tidy this up so it's not called every run
    val mappedClasses = Gson().fromJson<List<IdClass>>(Files.newBufferedReader(hooks), object : TypeToken<List<IdClass?>?>() {}.getType())
    val classManager = ClassNodeManager()

    override fun map(internalName: String): String {
        val foundClass = mappedClasses.filter { it.name == internalName }
        if (foundClass.any()) {
            return "f/" + foundClass.first().`class`
        }
        if(internalName.startsWith("f/"))  {
            return classManager.addClass(internalName)
        }

        return internalName
    }

    override fun mapMethodName(owner: String, name: String, descriptor: String): String {
        val foundMethod = mappedClasses.flatMap { it.methods }.filter { it.owner == owner && it.name == name }
        if (foundMethod.any()) {
            return foundMethod.first().method
        }
        if(owner.startsWith("f/"))  {
            return classManager.getClass(owner).addMethod(name, descriptor)
        }

        return name
    }

    override fun mapFieldName(owner: String, name: String, descriptor: String): String {
        val foundField = mappedClasses.flatMap { it.fields }.filter { it.owner == owner && it.name == name }
        if (foundField.any()) {
            return foundField.first().field
        }
        if(owner.startsWith("f/"))  {
            return classManager.getClass(owner).addField(name)
        }

        return name
    }

}