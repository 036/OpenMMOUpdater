package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.lang.reflect.Modifier

class World : IdentityMapper.Class() {
    override val predicate =
        predicateOf<ClassWrapper> { it.fields.any { field -> field.desc.equals("Ljava/util/concurrent/ConcurrentHashMap;") && field.access != 0 && Opcodes.ACC_STATIC != 0 }}
            .and { it.interfaces.any{iface -> iface.className.equals("java.lang.Iterable")} }
            .and { it.interfaces.any{iface -> iface.className.equals("com.badlogic.gdx.utils.Disposable")} }

    class getSpecificMapData() : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.arguments.size == 3 }
            .and { it.arguments.startsWith(Type.BYTE_TYPE, Type.BYTE_TYPE, Type.BYTE_TYPE) }
    }
}