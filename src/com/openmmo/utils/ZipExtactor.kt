package utils

import java.io.File

/*
ZIP Format Info - https://en.wikipedia.org/wiki/ZIP_(file_format)

How it works:
We are reading the PokeMMO.exe in and try to find the ZIP Header of the Jar
which is located somewhere in the data section of the Exe.
The Header signature we need to find is 'PK\x03\x04'
and the EOCD Signature is 'PK\x05\x06'.
We calculate the EOF by getting reading the EOCD structure at pos 20 - 22 as little endian value.
EOF = EOCD + 22 + EOCD[20:22]

 */

fun ExtractZip(file: File): ByteArray {
    val HeaderSig = arrayOf('P'.code.toByte(), 'K'.code.toByte(), 0x03.toByte(), 0x04.toByte() )
    val EOCDSig = arrayOf('P'.code.toByte(), 'K'.code.toByte(), 0x05.toByte(), 0x06.toByte() )

    val data = file.readBytes()
    val header = findSignature(data, HeaderSig)
    val eocd = findSignature(data, EOCDSig)
    println("Found Header at ${Integer.toHexString(header)}")
    println("Found EOCD at ${Integer.toHexString(eocd)}")

    val eof = eocd + 22 + littleEndianConversion(data.copyOfRange(eocd + 20, eocd + 22))
    println("Found OEF at ${Integer.toHexString(eof)}")

    return data.copyOfRange(header, eof)
}

fun findSignature(data: ByteArray, signature: Array<Byte>): Int {
    if(data.size < signature.size)
        return -1

    var counter = 0
    for (i in data.indices) {
        if(data[i] == signature[counter]) {
            counter++
            if (counter == signature.size)
                return i - signature.size + 1
        } else
            counter = 0
    }
    return -1
}

// We need to force the use of little endian https://stackoverflow.com/questions/56872782/convert-byte-array-to-int-odd-result-java-and-kotlin
fun littleEndianConversion(bytes: ByteArray): Int {
    var result = 0
    for (i in bytes.indices) {
        result = result or (bytes[i].toInt() shl 8 * i)
    }
    return result
}