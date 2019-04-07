package test.java

import com.finn.blackjack.Game
import com.finn.blackjack.Game.dealer
import com.finn.blackjack.Game.deck
import com.finn.blackjack.Game.sam
import com.finn.blackjack.GameLogic.Companion.checkForWinner
import com.finn.blackjack.GameLogic.Companion.playBlackjack
import com.finn.blackjack.GameUtils.Companion.DEFAULT_DIR
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import test.java.TestUtils.handWithValue

class GameLogicTest { //TODO: separate into class tests

    @Before
    fun setup() {
        Game.setUp("${DEFAULT_DIR}normalDeck.txt")
    }

    @Test
    fun game_setup_normal_deckAndPlayersReady() {
        playBlackjack()
    }

    @Test
    fun requirementsStalemate_suddenDeath() {
        sam().hand.addAll(handWithValue(18))
        dealer().hand.addAll(handWithValue(20))
        checkForWinner()
        assertThat(sam().hand.size, equalTo(1))
        assertThat(dealer().hand.size, equalTo(1))
        assertThat(Game.winner != null, equalTo(true))
    }



    @Test
    fun dealCards_cardsTakenFromTopOfTheDeck() {
        val topCard = deck().cards.first()
        sam().requestCard()
        assertThat(sam().hand[0].name, equalTo(topCard.name))
        assertThat(Game.deck.cards.size, equalTo(51))
        sam().hand.forEach {
            assertThat(Game.deck.cards.contains(it), equalTo(false))
        }
        dealer().hand.forEach {
            assertThat(Game.deck.cards.contains(it), equalTo(false))
        }
    }




}


