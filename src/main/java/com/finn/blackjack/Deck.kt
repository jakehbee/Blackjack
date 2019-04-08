package com.finn.blackjack

class Deck {
    lateinit var cards: MutableList<Card>
}

class Card(val name: String) {
    private val cardValue = name.parseValue()
    val value = cardValue

    enum class Suit { H, S, D, C }
}

fun Deck.shuffle() = this.cards.shuffle()


