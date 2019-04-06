package test.java

import com.finn.blackjack.Card
import com.finn.blackjack.Game
import com.finn.blackjack.GameUtils
import com.finn.blackjack.GameUtils.Companion.validCardValues


object TestUtils {

     fun populateDeck() {
        Game.deck!!.cards.addAll(GameUtils.readGameFileToCardList().cards)
    }

    //TODO: this is overhead, you can just put a file in resources
    fun writeNormalDeckToFile() = BlackjackTest.file.writeText(GameUtils.validCardValues().joinToString(", "))

    //TODO: same as above
    fun writeInvalidDeckToFile() {
        val cards = GameUtils.validCardValues()
        cards.addAll(listOf("J30", "P11", "X0", "JC"))
        BlackjackTest.file.writeText(cards.joinToString(","))
    }

    //TODO: same as above
    fun writeDoubleDeckToFile() {
        val doubleDeckContent = validCardValues()
        doubleDeckContent.addAll(validCardValues())
        BlackjackTest.file.writeText(doubleDeckContent.joinToString(", "))
    }

    fun isValidDeck(cardList: MutableList<String>): Boolean {
        cardList.forEach {
            if (!GameUtils.validCardValues().contains(it)) {
                return false
            }
        }
        return true

    }



    fun handWithValue(handValue:Int): MutableList<Card> {
        val mod = handValue%2
        val halfHandVal = handValue/2
     return mutableListOf(Card("H$halfHandVal"),Card("H${halfHandVal+mod}"))
    }


}
