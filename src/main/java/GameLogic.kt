package com.finn.blackjack

import com.finn.blackjack.Game.dealer
import com.finn.blackjack.Game.gameRound
import com.finn.blackjack.Game.incrementRound
import com.finn.blackjack.Game.sam


class GameLogic {
    companion object {

        fun playBlackjack() {
            Dealer.shuffleDeck()
            Dealer.deal()

            while (Game.winner == null) {
                continueGame()
                checkForWinner()
            }
           val loser =  when(Game.winner){
                sam() -> dealer()
               else -> sam()

            }
            val name = Game.winner!!.name
            val hand = Game.winner!!.hand.makePretty()
            println("$name")
            println("$name: $hand")
            println("${loser.name}: ${loser.hand.makePretty()}")
        }

        private fun continueGame() {
            incrementRound()
            sam().decideMove()
            checkForWinner()
            dealer().decideMove()
        }

        fun checkForWinner() {
            val samWinConditions = listOf(
                    dealer().hasBust() && dealer().handValue() != 22 && sam().handValue() != 22 && gameRound() != 0,
                    sam().hasBlackjack(),
                    (sam().hasBlackjack() && dealer().hasBlackjack() && gameRound() == 0))

            val dealerWinConditions = listOf(
                    sam().hasBust(),
                    dealer().hasBlackjack(),
                    (sam().handValue() == 22 && dealer().handValue() == 22 && Game.round == 0)
            )

            Game.winner = when {
                samWinConditions.atLeastOneBooleanConditionTrue() -> sam()
                dealerWinConditions.atLeastOneBooleanConditionTrue() -> dealer()
                else -> null
            }

            val stalemateConditions = listOf(
                    sam().handValue() >= 17,
                    dealer().handValue() > sam().handValue(),
                    Game.winner == null
            )

            //Escaping a stalemate
            if (stalemateConditions.allBooleanConditionsTrue()) {
                sam().limit = 21
            }
        }
    }
}

fun String.parseValue(): Int {//Splits string values into a pair of suit and value
    return when (val subStr = substring(1).toUpperCase()) {
        "A" -> 11
        "J", "Q", "K" -> 10
        else -> subStr.toInt()
    }
}

fun List<Boolean>.allBooleanConditionsTrue(): Boolean {
    return !this.contains(false)
}

fun List<Boolean>.atLeastOneBooleanConditionTrue(): Boolean {
    return this.contains(true)
}

fun MutableList<Card>.makePretty(): String {
    return this.joinToString { it.name }
}
