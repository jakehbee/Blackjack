package com.finn.blackjack

object Sam : Player(name = "sam", limit = 17, hand = mutableListOf()) {
    fun decideMove() {
        if (!this.hasBlackjack() && !this.hasBust() && this.handValue() <= this.limit && !Dealer.hasBlackjack()) {
            requestCard()
            decideMove()
        } else {
            return
        }
    }
}