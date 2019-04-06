package com.finn.blackjack

class Deck {
    val cards = mutableListOf<Card>()

}

class Card(val name:String,val value: Int, val suit: Suit){

    enum class Suit { H, S, D, C } //todo: suit is useless, remove.
}

fun Deck.shuffle() = this.cards.shuffle()

