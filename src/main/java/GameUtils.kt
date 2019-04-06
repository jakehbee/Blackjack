package com.finn.blackjack

import org.apache.commons.io.FilenameUtils
import java.io.File


class GameUtils {
    companion object {
        const val DEFAULT_FILE_PATH = "target/deck.txt"

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


        fun readGameFileToCardList(filePath: String? = DEFAULT_FILE_PATH): Deck {
            val gameFile = File(FilenameUtils.normalize(filePath))//hope to resolve mac/pc file separator difference
            val fileValues = gameFile.readText().split(",")
            val deck = Deck()

            fileValues.forEach {
                val cardName = it.trim()
                if (validCardValues().contains(cardName)) {
                    deck.cards.add(Card(name = cardName))
                }
            }
            return deck
        }

        fun checkForWinner() {
            Game.winner = if (Dealer.hasBust() || Sam.hasBlackjack() || (Sam.hasBlackjack() && Dealer.hasBlackjack())) {
                Sam
            } else {
                if (Sam.hasBust() || Dealer.hasBlackjack() || (Sam.handValue() == 22 && Dealer.handValue() == 22)) {
                    Dealer
                } else {
                    null
                }
            }

            //Escaping a stalemate
            if (Sam.handValue() >= 17 && Dealer.handValue() > Sam.handValue() && Game.winner == null) {
                suddenDeath()
            }
        }

        private fun suddenDeath() {
            Sam.hand.clear()
            Dealer.hand.clear()
            Sam.requestCard()
            Dealer.requestCard()

            when {
                (Sam.handValue() > Dealer.handValue()) -> Game.winner = Sam
                (Dealer.handValue() > Sam.handValue()) -> Game.winner = Dealer
                else -> suddenDeath()
            }
        }
    }
}

fun String.parseValue(): Pair<Int, Card.Suit> {//Splits string values into suit and value
    val suit = Card.Suit.valueOf(this.substring(0, 1))
    val value = when (val subStr = this.substring(1)) {
        "A" -> 11
        "J", "Q", "K" -> 10
        else -> subStr.toInt()
    }
    return Pair(value, suit)
}
