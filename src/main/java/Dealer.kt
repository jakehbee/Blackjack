package com.finn.blackjack

object Dealer : Player(name = "dealer", limit = 0, hand = mutableListOf()) {

    fun deal() {
        for (i in 0..1) {
            dealTo(Sam)
            dealTo(Dealer)
        }
        println()
    }

     fun dealTo(player: Player) {
        player.hand.add(drawCard())
    }

     fun drawCard(): Card {
        return Game.deck.cards.removeAt(0)
    }

    fun shuffleDeck() {
        Game.deck.shuffle()
    }
}