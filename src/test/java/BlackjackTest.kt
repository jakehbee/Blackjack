package test.java

import com.finn.blackjack.*
import com.finn.blackjack.Game.playBlackjack
import com.finn.blackjack.GameUtils.Companion.DEFAULT_FILE_PATH
import com.finn.blackjack.GameUtils.Companion.checkForWinner
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import test.java.TestUtils.handWithValue
import test.java.TestUtils.populateDeck
import test.java.TestUtils.writeDoubleDeckToFile
import test.java.TestUtils.writeInvalidDeckToFile
import test.java.TestUtils.writeNormalDeckToFile
import java.io.File

class BlackjackTest { //TODO: separate into class tests

    companion object {
        var file = File(DEFAULT_FILE_PATH)
    }

    @Before
    fun setup() {
        file.createNewFile()
        writeNormalDeckToFile()
    }

    @After
    fun tearDown() {
        if (file.exists()) {
            file.delete()
        }
        Game.winner = null
        Game.deck?.cards?.clear()
        Game.round = 0
        Sam.hand.clear()
        Dealer.hand.clear()
        Dealer.limit = 0
    }

    @Test
    fun game_setup_normal_deckAndPlayersReady() {
        playBlackjack()
    }


    @Test
    fun requirementsStalemate_suddenDeath() {
        populateDeck()
        Sam.hand.addAll(handWithValue(18))
        Dealer.hand.addAll(handWithValue(20))
        Game.round = 20
        checkForWinner()
        assertThat(Sam.hand.size, equalTo(1))
        assertThat(Dealer.hand.size, equalTo(1))
        assertThat(Game.winner != null, equalTo(true))
    }

//    @Test
//    fun samHasBust_gameEndsWithNoMoreCardsDrawn() {
//        Sam.hand.addAll(handWithValue(17))
//    }

    @Test
    fun samHasBlackjack_noMoreCardsDrawn() {

    }

    @Test
    fun playerDecisions_samScoreIs5_samRequestsCard() {
        populateDeck()
        Sam.hand.addAll(handWithValue(5))
        Sam.decideMove()
        assertThat(Sam.hand.size, equalTo(4)) //always 4, deck order is the same
    }

    @Test
    fun samHasBlackjack_dealerDoesNot() {
        Sam.hand.addAll(handWithValue(21))
        Dealer.hand.addAll(handWithValue(5))
        assertThat(Sam.hasBlackjack(), equalTo(true))
        assertThat(Dealer.hasBlackjack(), equalTo(false))
    }

    @Test
    fun dealerHasHigherScoreThanSam_dealerStops() {
        Dealer.hand.addAll(handWithValue(9))
        Sam.hand.addAll(handWithValue(6))
        Dealer.decideMove()
        assertThat(Dealer.hand.size, equalTo(2))
    }

    @Test
    fun dealCards_cardsTakenFromTopOfTheDeck() {
        populateDeck()
        Dealer.shuffleDeck()
        val topCard = Game.deck!!.cards.first()

        Dealer.deal()
        assertThat(Sam.hand[0].name, equalTo(topCard.name))
        assertThat(Game.deck!!.cards.size, equalTo(48))
        Sam.hand.forEach {
            assertThat(Game.deck!!.cards.contains(it), equalTo(false))
        }
        Dealer.hand.forEach {
            assertThat(Game.deck!!.cards.contains(it), equalTo(false))
        }
    }


    @Test
    fun readGameFile_doubleDeckFile_fileAccepted() {
        writeDoubleDeckToFile()
        val cardList = GameUtils.readGameFileToCardList()
        assertThat(cardList.cards.size, equalTo(104))
    }


    @Test
    fun game_setup_invalidDeck_deckCleanedAndPlayersReady() {
        writeInvalidDeckToFile()
        val cardList = GameUtils.readGameFileToCardList().cards.map { it.name }.toMutableList()
        assertThat(cardList.size, equalTo(52))
        assertThat(TestUtils.isValidDeck(cardList), equalTo(true))

    }

    fun corruptedFile_ErrorThrown() {
        TODO()
    }

}


