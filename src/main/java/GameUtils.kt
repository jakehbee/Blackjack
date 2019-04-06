package com.finn.blackjack

import java.io.File
import java.nio.file.Path

class GameUtils {

    companion object {
        const val DEFAULT_FILE_PATH = "target/deck.txt"

        fun getCards(): MutableList<String> {
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
            return deck
        }
    }

    fun readGameFileToCardList(filePath: String? = DEFAULT_FILE_PATH): MutableList<String> { //mac and pc
        val gameFile = File(filePath)
        val fileValues = gameFile.readText().split(",")
        val cardList = mutableListOf<String>()
        val validCardList = getCards()

        fileValues.forEach {
            val cardName = it.trim()
            if (validCardList.contains(cardName))
                cardList.add(cardName)
        }
        return cardList

    }


}
