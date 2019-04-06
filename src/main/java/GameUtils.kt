package com.finn.blackjack

import java.io.File
import java.nio.file.Path

class GameUtils{

    companion object {
       val DEFAULT_FILE_PATH = "target/"
    }


    fun writeNormalDeckToFile(filePath: String) { //Possibly customise ranges to provide different decks
        var cardVal: String
        val deck = mutableListOf<String>()
        for (a in 0..3) {
            for (i in 1..13) {
                cardVal = when (i) {
                    1 -> "A"
                    11 -> "J"
                    12 -> "Q"
                    13 -> "K"
                    else -> i.toString()
                }
                deck.add("${Deck.Suit.values()[a]}$cardVal")
            }
        }
        val file: File? = null
        file!!.writeText(deck.joinToString(", "))

    }

    fun readGameFile(filePath:String) { //mac and pc

        val separator =  System.getProperty("path.separator")
        println()
    }


    }
