package test.java

import com.finn.blackjack.GameUtils

fun MutableList<String>.isValidDeck(validDeck: MutableList<String>? = GameUtils.getCards()): Boolean {
    this.forEach {
        if(!validDeck!!.contains(it)){
            return false
        }
    }
    return true

}

object TestUtils{

         fun writeNormalDeckToFile() = BlackjackTest.file.writeText(GameUtils.getCards().joinToString(", "))

         fun writeInvalidDeckToFile() {
            val cards = GameUtils.getCards()
            cards.addAll(listOf("J30", "P11", "X0", "JC"))
            BlackjackTest.file.writeText(cards.joinToString(","))
        }



}
