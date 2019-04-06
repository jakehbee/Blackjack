package com.finn.blackjack

class Game {





     fun startGame() {
        setUp()
       deal()
         
    }

    fun setUp() {
        GameUtils.readGameFileToCardList()
    }
}