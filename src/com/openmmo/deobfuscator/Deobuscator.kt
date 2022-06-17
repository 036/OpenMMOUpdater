package com.openmmo.deobfuscator

import com.openmmo.deobfuscator.Common.Transformers.RenameFromHooks
import com.openmmo.deobfuscator.Common.Transformer
import com.openmmo.deobfuscator.Common.readClasses
import com.openmmo.deobfuscator.Common.writeClasses
import java.nio.file.Path

class Deobuscator {
    fun run(input: Path, output: Path, hooks: Path) {

        val transformer = Transformer.Composite(
            Transformer.Remap(RenameFromHooks(hooks)),
        )

        writeClasses(transformer.transform(readClasses(input)), output)
        println("Deob finished")
    }
}