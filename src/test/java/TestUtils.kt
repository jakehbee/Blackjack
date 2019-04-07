package test.java

import com.finn.blackjack.Card
import com.finn.blackjack.Game
import com.finn.blackjack.GameUtils


object TestUtils {

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
