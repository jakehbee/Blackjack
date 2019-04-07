package test.java

import com.finn.blackjack.Card
import com.finn.blackjack.Game
import com.finn.blackjack.GameUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class GameUtilsTest{

    @Before
    fun setup() {
        Game.setUp("${GameUtils.DEFAULT_DIR}normalDeck.txt")
    }


    @Test
    fun readGameFile_doubleDeckFile_fileAccepted() {
        Game.setUp("${GameUtils.DEFAULT_DIR}doubleDeck.txt")
        MatcherAssert.assertThat(Game.deck.cards.size, CoreMatchers.equalTo(104))
    }


    @Test
    fun game_setup_invalidDeck_deckCleanedAndPlayersReady() {
        Game.setUp("${GameUtils.DEFAULT_DIR}invalidDeck.txt")
        val cardList = Game.deck.cards.map { card: Card -> card.name }.toMutableList()
        MatcherAssert.assertThat(cardList.size, CoreMatchers.equalTo(52))
        MatcherAssert.assertThat(TestUtils.isValidDeck(cardList), CoreMatchers.equalTo(true))

    }

    @Test
    fun corruptedFile_defaultDeckUsed() {
        Game.setUp("${GameUtils.DEFAULT_DIR}corruptedFile.jpg")
        MatcherAssert.assertThat(Game.deck().cards.size, CoreMatchers.equalTo(52))
    }

    @Test
    fun csvFile_fileAccepted() {
        Game.setUp("${GameUtils.DEFAULT_DIR}csvFile.csv")
        MatcherAssert.assertThat(Game.deck().cards.size, CoreMatchers.equalTo(52))
        MatcherAssert.assertThat(Game.deck.cards.filter { it.name == "S2" }.size, CoreMatchers.equalTo(2)) //ensuring that default deck is not used
    }
    @Test
    fun csvFileWithQuotes_fileAccepted() {
        Game.setUp("${GameUtils.DEFAULT_DIR}csvFileWithQuotes.csv")
        MatcherAssert.assertThat(Game.deck().cards.size, CoreMatchers.equalTo(53))
        MatcherAssert.assertThat(Game.deck.cards.filter { it.name == "C4" }.size, CoreMatchers.equalTo(2))
    }

    @Test
    fun csvFile_inconsistentSpacing_fileAccepted() {
        Game.setUp("${GameUtils.DEFAULT_DIR}csvFileInconsistentSpacing.csv")
        MatcherAssert.assertThat(Game.deck().cards.size, CoreMatchers.equalTo(53))
        MatcherAssert.assertThat(Game.deck.cards.filter { it.name == "S10" }.size, CoreMatchers.equalTo(2))
    }

}