package com.finn.blackjack

import com.finn.blackjack.GameUtils.Companion.readGameFileToDeck


object Game {

    lateinit var deck: Deck
    var winner: Player? = null
    var round: Int = 0
    lateinit var players: MutableList<Player>

    fun setUp(filePath: String?) {
        this.deck = readGameFileToDeck(filePath)
    }


}
