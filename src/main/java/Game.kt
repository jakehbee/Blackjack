package com.finn.blackjack

import com.finn.blackjack.GameUtils.Companion.readGameFileToDeck


object Game {

    lateinit var deck: Deck
    var winner: Player? = null
    var round: Int = 0
    lateinit var players: MutableList<Player>

    fun setUp(filePath: String) {
        this.deck = readGameFileToDeck(filePath)
        players = mutableListOf(Sam(), Dealer())
    }

    fun deck(): Deck = this.deck
    fun sam(): Player = this.players[0]
    fun dealer(): Player = this.players[1]


}
