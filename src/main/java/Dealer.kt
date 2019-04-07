package com.finn.blackjack

object Dealer : Player(name = "dealer", limit = 0, hand = mutableListOf()) {

    val continueConditions = listOf(!this.hasBlackjack(), !this.hasBust(), this.handValue() <= this.limit && !Sam.hasBlackjack())

    fun deal() {
        for (i in 0..1) {
            dealTo(Sam)
            dealTo(Dealer)
        }
    }

    fun dealTo(player: Player) {
        player.hand.add(drawCard())
        if (player is Sam) {
            Dealer.limit = Sam.handValue()
        }

    }

    fun decideMove() {//TODO: All ifs could have listed continueConditions
        if (continueConditions.allAreMet()) {
            requestCard()
            decideMove()
        } else {
            return
        }
    }

    private fun drawCard(): Card {
        return Game.deck!!.cards.removeAt(0)
    }

    fun shuffleDeck() {
        Game.deck!!.shuffle()
    }

    private fun List<Boolean>.allAreMet(): Boolean {
        return !this.contains(false)
    }

}
