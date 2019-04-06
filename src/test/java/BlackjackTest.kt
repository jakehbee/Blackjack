package test.java

import com.finn.blackjack.Card
import com.finn.blackjack.Game
import com.finn.blackjack.GameUtils
import com.finn.blackjack.GameUtils.Companion.DEFAULT_FILE_PATH
import com.finn.blackjack.Sam
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import test.java.TestUtils.writeInvalidDeckToFile
import test.java.TestUtils.writeNormalDeckToFile
import java.io.File

class BlackjackTest {

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
        file.delete()
    }

    @Test
    fun game_setup_normal_deckAndPlayersReady() {
        val game = Game.startGame()
        GameUtils.readGameFileToCardList()
    }

    @Test
    fun samHasBlackjack_dealerDoesNot(){
        Sam.hand.addAll(listOf(Card("CA",11,Card.Suit.C),Card("D10",10,Card.Suit.D)))
        assertThat(Sam.hasBlackjack(), equalTo(true))

        Dealer.hand.addAll(listOf(Card("S2",11,Card.Suit.C),Card("H3",3,Card.Suit.D)))
        assertThat(Sam.hasBlackjack(), equalTo(true))
    }

    @Test
    fun dealCards_cardsRemovedFromDeck(){

    }



    @Test
    fun game_setup_invalidDeck_deckCleanedAndPlayersReady() {
        writeInvalidDeckToFile()
        val cardList = GameUtils.readGameFileToCardList().cards.map { it.name }.toMutableList()
        assertThat(cardList.size, equalTo(52))
        assertThat(TestUtils.isValidDeck(cardList), equalTo(true))

    }
}

