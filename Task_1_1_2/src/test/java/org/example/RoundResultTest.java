package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для RoundResult.
 */
class RoundResultTest {

    private Hand player;
    private Hand dealer;

    private Card ten;
    private Card nine;
    private Card eight;
    private Card five;

    @BeforeEach
    void set() {
        player = new Hand();
        dealer = new Hand();

        ten = new Card(Rank.TEN, Suit.SPADES);
        nine = new Card(Rank.NINE, Suit.HEARTS);
        eight = new Card(Rank.EIGHT, Suit.SPADES);
        five = new Card(Rank.FIVE, Suit.HEARTS);
    }

    @Test
    void testPlayerBust() {
        player.addCard(ten);
        player.addCard(ten);
        player.addCard(ten);
        dealer.addCard(five);

        assertEquals(RoundResult.DEALER_WIN, RoundResult.takeResult(player, dealer));
    }

    @Test
    void testDealerBust() {
        player.addCard(five);
        dealer.addCard(ten);
        dealer.addCard(ten);
        dealer.addCard(ten);

        assertEquals(RoundResult.PLAYER_WIN, RoundResult.takeResult(player, dealer));
    }

    @Test
    void testPlayerHigher() {
        player.addCard(ten);
        player.addCard(nine);
        dealer.addCard(ten);
        dealer.addCard(five);

        assertEquals(RoundResult.PLAYER_WIN, RoundResult.takeResult(player, dealer));
    }

    @Test
    void testDealerHigher() {
        player.addCard(ten);
        player.addCard(five);
        dealer.addCard(ten);
        dealer.addCard(nine);

        assertEquals(RoundResult.DEALER_WIN, RoundResult.takeResult(player, dealer));
    }

    @Test
    void testDraw() {
        player.addCard(ten);
        player.addCard(eight);
        dealer.addCard(ten);
        dealer.addCard(eight);

        assertEquals(RoundResult.DRAW, RoundResult.takeResult(player, dealer));
    }

    @Test
    void testBothBust() {
        player.addCard(ten);
        player.addCard(ten);
        player.addCard(ten);
        dealer.addCard(ten);
        dealer.addCard(ten);
        dealer.addCard(ten);

        assertEquals(RoundResult.DEALER_WIN, RoundResult.takeResult(player, dealer));
    }
}