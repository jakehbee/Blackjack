package com.finn.blackjack

import com.finn.blackjack.GameUtils.Companion.checkBlackjack

object Game {
    val deck: Deck = GameUtils.readGameFileToCardList()
    var winner: Player? =null

    fun startGame() {
        Dealer.shuffleDeck()
        Dealer.deal()
        checkBlackjack()
    }


}