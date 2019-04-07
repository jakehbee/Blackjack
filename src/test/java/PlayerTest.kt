package test.java

import com.finn.blackjack.Game
import com.finn.blackjack.GameUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class PlayerTest{

    @Before
    fun setup() {
        Game.setUp("${GameUtils.DEFAULT_DIR}normalDeck.txt")
    }

    @Test
    fun samHasBust_gameEndsWithNoMoreCardsDrawn() {
        Game.sam().hand.addAll(TestUtils.handWithValue(17))
    }

    @Test
    fun samHasBlackjack_samStops() {
        Game.sam().hand.addAll(TestUtils.handWithValue(21))
        Game.sam().decideMove()
    }

    @Test
    fun samHitsLimit_samStops() {
        Game.sam().hand.addAll(TestUtils.handWithValue(17))
        Game.sam().decideMove()
        MatcherAssert.assertThat(Game.sam().hand.size, CoreMatchers.equalTo(2))
    }

    @Test
    fun dealerHasBlackjack_dealerStops() {
        Game.sam().hand.addAll(TestUtils.handWithValue(21))
        Game.dealer().decideMove()
        MatcherAssert.assertThat(Game.dealer().hand.size, CoreMatchers.equalTo(0))
    }

    @Test
    fun dealerHitsLimit_dealerStops() {
        Game.sam().hand.addAll(TestUtils.handWithValue(4))
        Game.dealer().hand.addAll(TestUtils.handWithValue(5))
        Game.sam().requestCard()
        MatcherAssert.assertThat(Game.dealer().hand.size, CoreMatchers.equalTo(2))

    }

    @Test
    fun samScoreBelowLimit_samRequestsCard() {
        Game.sam().hand.addAll(TestUtils.handWithValue(5))
        Game.sam().decideMove()
        MatcherAssert.assertThat(Game.sam().hand.size, CoreMatchers.equalTo(4)) //always 4, deck order is the same
    }

    @Test
    fun playersHaveEqualHandValue_dealerContinues() {
        Game.sam().hand.addAll(TestUtils.handWithValue(11))
        Game.dealer().hand.addAll(TestUtils.handWithValue(11))
        Game.dealer().decideMove()
        MatcherAssert.assertThat(Game.dealer().hand.size, CoreMatchers.equalTo(3))
    }

    @Test
    fun samHasBlackjack_dealerDoesNot() {
        Game.sam().hand.addAll(TestUtils.handWithValue(21))
        Game.dealer().hand.addAll(TestUtils.handWithValue(5))
        MatcherAssert.assertThat(Game.sam().hasBlackjack(), CoreMatchers.equalTo(true))
        MatcherAssert.assertThat(Game.dealer().hasBlackjack(), CoreMatchers.equalTo(false))
    }

    @Test
    fun dealerHasHigherScoreThanSam_dealerStops() {
        Game.dealer().hand.addAll(TestUtils.handWithValue(9))
        Game.sam().hand.addAll(TestUtils.handWithValue(6))
        Game.dealer().decideMove()
        MatcherAssert.assertThat(Game.dealer().hand.size, CoreMatchers.equalTo(2))
    }
}