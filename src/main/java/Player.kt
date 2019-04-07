package com.finn.blackjack

open class Player(val name: String,
                  var limit: Int,
                  val hand: MutableList<Card>) {

    fun handValue() = this.hand.sumBy { it.value }

    fun hasBlackjack() = (handValue() == 21)

    fun hasBust() = (handValue() > 21)

    fun requestCard() =Dealer.dealTo(this)

    open fun decideMove() {}


}
