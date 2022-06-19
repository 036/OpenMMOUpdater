package com.openmmo.analysers

import com.openmmo.mapper.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class ChatTypeEnum : IdentityMapper.Enum("NORMAL", "SHOUT", "WHISPER", "TRADE", "GLOBAL", "CHANNEL", "GUILD", "LINK") {
    @DependsOn(ChatTypeEnum::class)
    class NORMAL : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[0] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class SHOUT : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[1] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class WHISPER : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[2] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class TRADE : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[3] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class GLOBAL : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[4] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class CHANNEL : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[5] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class GUILD : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[6] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class LINK : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[7] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class SYSTEM_ANNOUNCEMENTS : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[8] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class GAME_NOTIFICATIONS : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[9] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class BATTLE : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == type<ChatTypeEnum>() }
            .and { it.hasAccess(Opcodes.ACC_FINAL) }
            .and { it.hasAccess(Opcodes.ACC_ENUM) }
            .and { it.klass.staticFields.filter { f -> f.type == type<ChatTypeEnum>() && f.hasAccess(Opcodes.ACC_ENUM) }[10] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class allTypes : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.sort == Type.ARRAY && it.type.elementType == type<ChatTypeEnum>() }
            .and { it.klass.fields.filter { f -> f.type.sort == Type.ARRAY && f.type.elementType == type<ChatTypeEnum>() }[1] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class activeTypes : IdentityMapper.StaticField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.sort == Type.ARRAY && it.type.elementType == type<ChatTypeEnum>() }
            .and { it.klass.fields.filter { f -> f.type.sort == Type.ARRAY && f.type.elementType == type<ChatTypeEnum>() }[0] == it }
    }

    @DependsOn(ChatTypeEnum::class)
    class commands : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.sort == Type.ARRAY && it.type.elementType.className.contains("String") }
            .and { it.klass.instanceFields.filter { f -> f.type.sort == Type.ARRAY && it.type.elementType.className.contains("String") }[0] == it }
            .and { it.klass.type == type<ChatTypeEnum>() }
    }

    @DependsOn(ChatTypeEnum::class)
    class commandsWithEndingSpace : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.sort == Type.ARRAY && it.type.elementType.className.contains("String") }
            .and { it.klass.instanceFields.filter { f -> f.type.sort == Type.ARRAY && it.type.elementType.className.contains("String") }[1] == it }
            .and { it.klass.type == type<ChatTypeEnum>() }
    }

    @DependsOn(ChatTypeEnum::class)
    class name : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type.sort == Type.OBJECT && it.type.baseType.className.contains("String") }
            .and { it.klass.type == type<ChatTypeEnum>() }
    }

    @DependsOn(ChatTypeEnum::class)
    class stringID : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.INT_TYPE }
            .and { it.klass.type == type<ChatTypeEnum>() }
    }

    @DependsOn(ChatTypeEnum::class)
    class id : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.BYTE_TYPE }
            .and { it.klass.type == type<ChatTypeEnum>() }
    }

    @DependsOn(ChatTypeEnum::class)
    class bitMask : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<FieldWrapper> { it.type == Type.SHORT_TYPE }
            .and { it.klass.type == type<ChatTypeEnum>() }
    }


    @DependsOn(ChatTypeEnum::class)
    class valueOf : IdentityMapper.StaticMethod() {
        override val predicate = predicateOf<MethodWrapper> { it.name == "valueOf" }
            .and { it.klass.type == type<ChatTypeEnum>() }
    }
}