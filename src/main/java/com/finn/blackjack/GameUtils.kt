package com.finn.blackjack

import com.finn.blackjack.Game.DEFAULT_DIR
import org.apache.commons.io.FilenameUtils.normalize
import java.io.File

class GameUtils {

    companion object {

        fun validCardValues(): MutableList<String> {//generate all cards in a deck
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
            var deck = Deck()
            val fileValues:List<String>
            deck.cards = mutableListOf()
            val defaultDeck = "${DEFAULT_DIR}normalDeck.txt"
            if (!File(filePath).isFile) {
                fileValues = validCardValues()
            } else {
                val gameFile = File(normalize(filePath))// Mac/PC
                fileValues = gameFile.readText().split(",", "\"")
            }
                fileValues.forEach {
                    val cardName = it.trim().toUpperCase()
                    if (validCardValues().contains(cardName)) {
                        deck.cards.add(Card(name = cardName))
                    }
                }
                if (deck.cards.sumBy { it.value } < 42) {//Possibility that no player can win
                    deck = readGameFileToDeck(defaultDeck)
                }
            return deck
        }
        }
    }
