package com.openmmo.analysers.Interfaces

import com.openmmo.mapper.*

class FileFilterInterface : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.interfaces.any { it.className.contains("FileFilter") }}
        .and { it.interfaces.any { it.className.contains("FilenameFilter") }}
}