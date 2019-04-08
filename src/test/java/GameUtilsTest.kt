package test.java

import com.finn.blackjack.Card
import com.finn.blackjack.Game
import com.finn.blackjack.Game.DEFAULT_DIR
import com.finn.blackjack.Game.setUp
import com.finn.blackjack.GameUtils.Companion.validCardValues
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.File

class GameUtilsTest {

    @Before
    fun setup() = setUp()

    @Test
    fun fourCardsInDeck_defaultDeckUsed() {
        setUp("${DEFAULT_DIR}fourCardDeck.txt")
        assertThat(Game.deck.cards.size, CoreMatchers.equalTo(52))
    }

    @Test
    fun doubleDeckFile_fileAccepted() {
        setUp("${DEFAULT_DIR}doubleDeck.txt")
        assertThat(Game.deck.cards.size, CoreMatchers.equalTo(104))
    }

    @Test
    fun invalidDeck_deckCleanedAndPlayersReady() {
        setUp("${DEFAULT_DIR}invalidDeck.txt")
        val cardList = Game.deck.cards.map { card: Card -> card.name }.toMutableList()
        assertThat(cardList.size, CoreMatchers.equalTo(53)) //ensuring that default deck is not used
        assertThat(TestUtils.isValidDeck(cardList), CoreMatchers.equalTo(true))
        assertThat(Game.deck.cards.filter { it.name == "HA" }.size, CoreMatchers.equalTo(2)) //ensuring that default deck is not used
    }

    @Test
    fun corruptedFile_defaultDeckUsed() {
        setUp("${DEFAULT_DIR}corruptedFile.jpg")
        assertThat(Game.deck().cards.size, CoreMatchers.equalTo(52))
    }

    @Test
    fun csvFile_fileAccepted() {
        setUp("${DEFAULT_DIR}csvFile.csv")
        assertThat(Game.deck().cards.size, CoreMatchers.equalTo(52))
        assertThat(Game.deck.cards.filter { it.name == "S2" }.size, CoreMatchers.equalTo(2))
    }

    @Test
    fun csvFileWithQuotes_fileAccepted() {
        setUp("${DEFAULT_DIR}csvFileWithQuotes.csv")
        assertThat(Game.deck().cards.size, CoreMatchers.equalTo(53))
        assertThat(Game.deck.cards.filter { it.name == "C4" }.size, CoreMatchers.equalTo(2))
    }

    @Test
    fun csvFile_inconsistentSpacing_fileAccepted() {
        setUp("${DEFAULT_DIR}csvFileInconsistentSpacing.csv")
        assertThat(Game.deck().cards.size, CoreMatchers.equalTo(53))
        assertThat(Game.deck.cards.filter { it.name == "S10" }.size, CoreMatchers.equalTo(2))
    }

    @Test
    fun lowerCaseDeck_fileAccepted() {
        setUp("${DEFAULT_DIR}lowerCaseDeck.txt")
        assertThat(Game.deck().cards.size, CoreMatchers.equalTo(53))
        assertThat(Game.deck.cards.filter { it.name == "DJ" }.size, CoreMatchers.equalTo(2))
    }

    @Test
    fun pointValueDeck_fileAcceptedDeckCleaned() {
        setUp("${DEFAULT_DIR}pointValueDeck.txt")
        assertThat(Game.deck().cards.size, CoreMatchers.equalTo(49))
    }

    @Test
    fun generateDeck_deckSameAsNormalDeckFile() {
        val deck = validCardValues()
        val normalDeck = File("src/main/resources/normalDeck.txt").readLines().toString()
        assertThat(deck.toString(), equalTo(normalDeck))
    }

    @Test
    fun literalCardValueTest() {  //Ugly but since its's a requirement, I've secured it with a test
        assertThat(Card("HA").value, equalTo(11))
        assertThat(Card("H2").value, equalTo(2))
        assertThat(Card("H3").value, equalTo(3))
        assertThat(Card("H4").value, equalTo(4))
        assertThat(Card("H5").value, equalTo(5))
        assertThat(Card("H6").value, equalTo(6))
        assertThat(Card("H7").value, equalTo(7))
        assertThat(Card("H8").value, equalTo(8))
        assertThat(Card("H9").value, equalTo(9))
        assertThat(Card("H10").value, equalTo(10))
        assertThat(Card("HJ").value, equalTo(10))
        assertThat(Card("HQ").value, equalTo(10))
        assertThat(Card("HK").value, equalTo(10))
        assertThat(Card("H99").value, equalTo(99))
        assertThat(Card("H0").value, equalTo(0))

    }
}