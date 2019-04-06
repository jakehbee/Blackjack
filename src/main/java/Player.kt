package com.finn.blackjack

open class Player(val name: String,
                  val limit: Int,
                  val hand: MutableList<Card>) {

    fun hasBlackjack() = (this.hand.sumBy { it.value } == 21)


}