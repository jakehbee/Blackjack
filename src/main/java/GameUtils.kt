package com.finn.blackjack

import org.apache.commons.io.FilenameUtils
import java.io.File

class GameUtils {

    companion object {
        const val DEFAULT_DIR = "src/main/resources/" //TODO, extract to game, so we can just set file name here


        fun validCardValues(): MutableList<String> {//generate all card values
            val validValues = mutableListOf<String>()
            var cardVal: String
            for (a in 0..3) {
                for (i in 1..13) {
                    cardVal = when (i) {
                        1 -> "A"
                        11 -> "J"
                        12 -> "Q"
                        13 -> "K"
                        else -> i.toString()
                    }
                    validValues.add("${Card.Suit.values()[a]}$cardVal")
                }
            }
            return validValues
        }


        fun readGameFileToDeck(filePath: String): Deck {
            val gameFile = File(FilenameUtils.normalize(filePath))//hope to resolve mac/pc file separator difference
            val fileValues = gameFile.readText().split(",", "\"")


            var deck = Deck()
            deck.cards = mutableListOf()

            fileValues.forEach {
                val cardName = it.trim()
                if (validCardValues().contains(cardName)) {
                    deck.cards.add(Card(name = cardName))
                }
            }
            if (fileValues.size < 4) {
               deck=  readGameFileToDeck("${DEFAULT_DIR}normalDeck.txt")
            }

            return deck
        }
    }
}