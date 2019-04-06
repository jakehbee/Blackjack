package test.java

import com.finn.blackjack.Card
import com.finn.blackjack.GameUtils



object TestUtils{

         fun writeNormalDeckToFile() = BlackjackTest.file.writeText(GameUtils.validCardValues().joinToString(", "))

         fun writeInvalidDeckToFile() {
            val cards = GameUtils.validCardValues()
            cards.addAll(listOf("J30", "P11", "X0", "JC"))
            BlackjackTest.file.writeText(cards.joinToString(","))
        }

    fun isValidDeck(cardList: MutableList<String>): Boolean {
        cardList.forEach {
            if (!GameUtils.validCardValues().contains(it)) {
                return false
            }
        }
        return true

    }



}
