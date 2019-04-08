package com.finn.blackjack

import com.finn.blackjack.Game.dealer
import com.finn.blackjack.Game.deck
import com.finn.blackjack.Game.sam

class Dealer : Player(name = "dealer", limit = 0, hand = mutableListOf()) {

    companion object {
        fun deal() {
            for (i in 0..1) {
                dealTo(sam())
                dealTo(dealer())
            }
        }

        fun dealTo(player: Player) = player.hand.add(drawCard())

        private fun drawCard()= deck().cards.removeAt(0)

        fun shuffleDeck()=deck().shuffle()
    }
    override fun decideMove() {
        dealer().limit = sam().handValue()

        val continueConditions = listOf(
                !dealer().hasBlackjack(),
                !dealer().hasBust(),
                dealer().handValue() <= dealer().limit,
                !sam().hasBlackjack())

        if (continueConditions.allBooleanConditionsTrue()) {
            dealer().requestCard()
            decideMove()
        } else {
            return
        }
    }
}

