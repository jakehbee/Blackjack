package com.finn.blackjack

import com.finn.blackjack.GameUtils.Companion.readGameFileToDeck


object Game {
    const val DEFAULT_DIR = "src/main/resources/"

    lateinit var deck: Deck
    var winner: Player? = null
    var round: Int = 0
    private lateinit var players: MutableList<Player>

    fun setUp(filePath: String? = null) {
        val gameFile = filePath ?: "${DEFAULT_DIR}normalDeck.txt"
        this.deck = readGameFileToDeck(gameFile)
        players = mutableListOf(Sam(), Dealer())
    }

    fun deck(): Deck = this.deck
    fun sam(): Player = this.players[0]
    fun dealer(): Player = this.players[1]
    fun gameRound() = this.round
    fun incrementRound() {
        this.round += 1
    }


}
