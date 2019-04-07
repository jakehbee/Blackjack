package com.finn.blackjack

import com.finn.blackjack.Game.dealer
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
            val name = Game.winner!!.name
            val hand = Game.winner!!.hand.makePretty()
            println("$name $hand")
            println("sam hand value ${sam().handValue()}")
            println("dealer hand value ${dealer().handValue()}")
        }

        private fun continueGame() {
            sam().decideMove()
            checkForWinner()
            dealer().decideMove()
            Game.round += 1
        }



        fun checkForWinner() {
            val samWinConditions = listOf(
                    dealer().hasBust(),
                    sam().hasBlackjack(),
                    (sam().hasBlackjack() && dealer().hasBlackjack() && Game.round == 0))

            val dealerWinConditions = listOf(
                    sam().hasBust(),
                    dealer().hasBlackjack(),
                    (sam().handValue() == 22 && dealer().handValue() == 22)

            )

            Game.winner =when{
                samWinConditions.atLeastOneBooleanConditionTrue() ->sam()
                dealerWinConditions.atLeastOneBooleanConditionTrue() ->dealer()
                else -> null
            }

            val stalemateConditions = listOf(
                    sam().handValue() >= 17,
                    dealer().handValue() > sam().handValue(),
                    Game.winner == null
            )



            //Escaping a stalemate
            if (stalemateConditions.allBooleanConditionsTrue()) {
                suddenDeath()
            }
        }

        private fun suddenDeath() {
            sam().hand.clear()
            dealer().hand.clear()
            sam().requestCard()
            dealer().requestCard()

            when {
                (sam().handValue() > dealer().handValue()) -> Game.winner = sam()
                (dealer().handValue() > sam().handValue()) -> Game.winner = dealer()
                else -> suddenDeath()
            }
        }
    }
}

fun String.parseValue(): Pair<Int, Card.Suit> {//Splits string values into suit and value
    val suit = Card.Suit.valueOf(this.substring(0, 1))
    val value = when (val subStr = this.substring(1)) {
        "A" -> 11
        "J", "Q", "K" -> 10
        else -> subStr.toInt()
    }
    return Pair(value, suit)
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
