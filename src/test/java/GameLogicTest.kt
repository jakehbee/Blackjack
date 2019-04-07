package test.java

import com.finn.blackjack.Card
import com.finn.blackjack.Dealer
import com.finn.blackjack.Game
import com.finn.blackjack.Game.dealer
import com.finn.blackjack.Game.deck
import com.finn.blackjack.Game.sam
import com.finn.blackjack.GameLogic.Companion.checkForWinner
import com.finn.blackjack.GameLogic.Companion.playBlackjack
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import test.java.TestUtils.handWithValue

class GameLogicTest {

    @Before
    fun setup() = Game.setUp()


    @Test //todo: delete
    fun startGame() {
        Dealer.shuffleDeck()
        playBlackjack()
    }

    @Test
    fun stalemate_samContinues() {
        sam().hand.addAll(handWithValue(18))
        dealer().hand.addAll(handWithValue(20))
        checkForWinner()
        sam().decideMove()
        checkForWinner()
        assertThat(sam().limit, equalTo(21))
        assertThat(Game.winner != null, equalTo(true))
    }


    @Test
    fun samHasBlackjack_dealerDoesNot_samWins() {
        sam().hand.addAll(TestUtils.handWithValue(21))
        dealer().hand.addAll(TestUtils.handWithValue(5))
        checkForWinner()
        assertThat(sam().hasBlackjack(), CoreMatchers.equalTo(true))
        assertThat(dealer().hasBlackjack(), CoreMatchers.equalTo(false))
        assertThat(Game.winner!!.name, equalTo("sam"))
    }

    @Test
    fun samAndDealerHaveBlackjack_samWins() {
        sam().hand.addAll(TestUtils.handWithValue(21))
        dealer().hand.addAll(TestUtils.handWithValue(21))
        checkForWinner()
        assertThat(sam().hasBlackjack(), CoreMatchers.equalTo(true))
        assertThat(dealer().hasBlackjack(), CoreMatchers.equalTo(true))
        assertThat(Game.winner!!.name, equalTo("sam"))
    }

    @Test
    fun samAndDealerHave22_dealerWins() {
        sam().hand.addAll(TestUtils.handWithValue(22))
        dealer().hand.addAll(TestUtils.handWithValue(22))
        checkForWinner()
        assertThat(sam().hasBlackjack(), CoreMatchers.equalTo(false))
        assertThat(dealer().hasBlackjack(), CoreMatchers.equalTo(false))
        assertThat(Game.winner!!.name, equalTo("dealer"))
    }

    @Test
    fun neitherPlayerHasBlackjack_samStartsDrawing() {
        Game.setUp("${Game.DEFAULT_DIR}allFives.txt")
        sam().hand.addAll(TestUtils.handWithValue(6))
        Game.deck().cards.clear()
        for (i in 0..4) {
            Game.deck.cards.add(Card("C5"))
        }
        playBlackjack()
        assertThat(sam().hand.size, equalTo(5))
        assertThat(deck().cards.isEmpty(), equalTo(true))
        assertThat(dealer().hand.size, equalTo(2))//no more than initial deal
        assertThat(Game.winner!!.name, equalTo("sam"))
    }

    @Test
    fun dealCards_unshuffledCardsTakenFromTopOfTheDeck_alternatingOrderWithSamFirst() {
        Dealer.deal()
        assertThat(sam().hand[0].name, equalTo("HA"))
        assertThat(sam().hand[1].name, equalTo("H3"))
        assertThat(dealer().hand[0].name, equalTo("H2"))
        assertThat(dealer().hand[1].name, equalTo("H4"))
        assertThat(Game.deck.cards.size, equalTo(48))
        sam().hand.forEach {
            assertThat(Game.deck.cards.contains(it), equalTo(false))
        }
        dealer().hand.forEach {
            assertThat(Game.deck.cards.contains(it), equalTo(false))
        }
    }


}


