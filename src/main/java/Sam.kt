package com.finn.blackjack

import com.finn.blackjack.Game.dealer
import com.finn.blackjack.Game.sam

class Sam : Player(name = "sam", limit = 17, hand = mutableListOf()) {

    override fun decideMove() {

        val continueConditions = listOf(
                !sam().hasBlackjack(),
                !sam().hasBust(),
                sam().handValue() < sam().limit,
                !dealer().hasBlackjack()
        )

        if (continueConditions.allBooleanConditionsTrue()) {
            println("Sam continues ${sam().handValue()}")
            sam().requestCard()
            decideMove()
        } else {
            return
        }
    }
}