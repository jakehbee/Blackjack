package test.java

import com.finn.blackjack.GameUtils
import com.finn.blackjack.GameUtils.Companion.DEFAULT_FILE_PATH
import org.junit.After
import org.junit.Before
import org.junit.Test;
import java.io.File

class BlackjackTest {

    companion object {
        var file: File? = null

    }


    @Before
    fun setup() {
        file = File(DEFAULT_FILE_PATH)
        val gameUtils =GameUtils()
       gameUtils.writeNormalDeckToFile(DEFAULT_FILE_PATH)
    }

    @After
    fun tearDown() {
        file = File("target/deck.txt")
        file!!.delete()
    }


    @Test
    fun game_setup_normal_deckAndPlayersReady() {


    }


}

