package com.openmmo.deobfuscator

import com.openmmo.deobfuscator.Common.Transformers.Rename
import com.openmmo.deobfuscator.Common.Transformer
import com.openmmo.deobfuscator.Common.readClasses
import com.openmmo.deobfuscator.Common.writeClasses
import java.nio.file.Path

class Deobuscator {
    fun run(input: Path, output: Path) {

        val transformer = Transformer.Composite(
            Transformer.Remap(Rename)
        )

        writeClasses(transformer.transform(readClasses(input)), output)
        println("Deob finished")
    }
}