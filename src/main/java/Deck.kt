package com.finn.blackjack

class Deck {
    val cards = mutableListOf<Card>()
}

class Card(val name: String) {
    private val parsedCardValues = name.parseValue()
    val value = parsedCardValues.first
    enum class Suit { H, S, D, C }
}

fun Deck.shuffle() {
    return this.cards.shuffle()
}


