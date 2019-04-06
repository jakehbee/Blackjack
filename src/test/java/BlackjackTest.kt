package test.java

import com.finn.blackjack.GameUtils
import com.finn.blackjack.GameUtils.Companion.DEFAULT_FILE_PATH
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import test.java.TestUtils.Companion.writeInvalidDeckToFile
import test.java.TestUtils.Companion.writeNormalDeckToFile
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
        writeNormalDeckToFile()
        val gameUtils = GameUtils()
        gameUtils.readGameFileToCardList()
    }

    @Test
    fun game_setup_invalidDeck_deckCleanedAndPlayersReady() {
        writeInvalidDeckToFile()
        val gameUtils = GameUtils()
        val cardList = gameUtils.readGameFileToCardList()
        assertThat(cardList.size, equalTo(52))
        assertThat(cardList.isValidDeck(), equalTo(true))
    }
}

