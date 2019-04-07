package test.java

import com.finn.blackjack.Game
import com.finn.blackjack.Game.dealer
import com.finn.blackjack.Game.sam
import com.finn.blackjack.GameLogic.Companion.checkForWinner
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class PlayerTest {

    @Before
    fun setup() = Game.setUp()

    @Test
    fun samHasBust_gameEndsWithNoMoreCardsDrawn() {
        sam().hand.addAll(TestUtils.handWithValue(23))
        dealer().hand.addAll(TestUtils.handWithValue(12))
        checkForWinner()
        assertThat(Game.winner!!.name, equalTo("dealer"))
    }

    @Test
    fun samHasBlackjack_samStops() {
        sam().hand.addAll(TestUtils.handWithValue(21))
        sam().decideMove()
    }

    @Test
    fun samHitsLimit_samStops() {
        sam().hand.addAll(TestUtils.handWithValue(17))
        sam().decideMove()
        assertThat(sam().hand.size, equalTo(2))
    }

    @Test
    fun dealerHasBlackjack_dealerStops() {
        sam().hand.addAll(TestUtils.handWithValue(21))
        dealer().decideMove()
        assertThat(dealer().hand.size, equalTo(0))
    }

    @Test
    fun dealerCardsHigherThanSam_dealerStops() {
        sam().hand.addAll(TestUtils.handWithValue(4))
        dealer().hand.addAll(TestUtils.handWithValue(5))
        dealer().decideMove()
        assertThat(dealer().hand.size, equalTo(2))
    }

    @Test
    fun samScoreBelowLimit_samRequestsCard() {
        sam().hand.addAll(TestUtils.handWithValue(5))
        sam().decideMove()
        assertThat(sam().hand.size, equalTo(4)) //always 4, deck order is the same
    }

    @Test
    fun playersHaveEqualHandValue_dealerContinues() {
        sam().hand.addAll(TestUtils.handWithValue(11))
        dealer().hand.addAll(TestUtils.handWithValue(11))
        dealer().decideMove()
        assertThat(dealer().hand.size, equalTo(3))
    }

    @Test
    fun dealerHasHigherScoreThanSam_dealerStops() {
        dealer().hand.addAll(TestUtils.handWithValue(9))
        sam().hand.addAll(TestUtils.handWithValue(6))
        dealer().decideMove()
        assertThat(dealer().hand.size, equalTo(2))
    }
}