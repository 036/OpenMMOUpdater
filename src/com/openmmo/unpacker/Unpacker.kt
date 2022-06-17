package com.openmmo.unpacker

import utils.ExtractZip
import java.nio.file.Path

class Unpacker {

    fun run(pathToExe: Path, pathToJar: Path) {
        val data = ExtractZip(pathToExe.toFile())
        pathToJar.toFile().writeBytes(data)
        return
    }
}