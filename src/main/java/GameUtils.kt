package com.finn.blackjack

import org.apache.commons.io.FilenameUtils
import java.io.File


class GameUtils {
    companion object {
        const val DEFAULT_FILE_PATH = "target/deck.txt"

        fun validCardValues(): MutableList<String> {
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


        fun readGameFileToCardList(filePath: String? = DEFAULT_FILE_PATH): Deck { //mac and pc?
            val gameFile = File(FilenameUtils.normalize(filePath))
            val fileValues = gameFile.readText().split(",")
            val deck = Deck()

            fileValues.forEach {
                val cardName = it.trim()
                if (validCardValues().contains(cardName))
                    deck.cards.add(cardName.toCard())
            }
            return deck
        }

        fun checkBlackjack()
        {
           if(Sam.hasBlackjack()) {
               Game.winner =Sam
           }
        }
    }

}

private fun String.toCard(): Card {
    val suit = Card.Suit.valueOf(this.substring(0, 1))
    val value = when (val subStr = this.substring(1)) {
        "A" -> 11
        "J", "Q", "K" -> 10
        else -> subStr.toInt()
    }

    return Card(this, value, suit)
}
