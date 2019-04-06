package com.finn.blackjack

import java.io.File

class FileReader(){
    val path = ""  //TODO: Mac and PC support


    companion object {
        fun readFile(fileReader: FileReader) :String
        = File(fileReader.path).readText(Charsets.UTF_8)
    }
}


