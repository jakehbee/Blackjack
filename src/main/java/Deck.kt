package com.finn.blackjack

class Deck {
    val cards = listOf<Card>()


    val values = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    enum class Suit { H, S, D, C }
}

class Card(value: Int, suit: Deck.Suit)
