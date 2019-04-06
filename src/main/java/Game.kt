package com.finn.blackjack

import com.finn.blackjack.GameUtils.Companion.checkForWinner
import com.finn.blackjack.GameUtils.Companion.readGameFileToCardList

object Game { //TODO: Is a separate game class needed?
    var deck: Deck? = readGameFileToCardList() //TODO:try and break this
    var winner: Player? = null
    var round: Int = 0

    fun playBlackjack() {
        Dealer.shuffleDeck()
        Dealer.deal()

        while (winner == null) {
            continueGame()
            checkForWinner()
        }
        val name = winner!!.name
        val hand = winner!!.hand.makePretty()
        println("$name $hand")
    }

    private fun continueGame() {
        Sam.decideMove()
        Dealer.decideMove()
        round += 1

    }
}

fun MutableList<Card>.makePretty(): String {
    return this.joinToString { it.name }
}
