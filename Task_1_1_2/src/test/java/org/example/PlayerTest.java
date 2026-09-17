package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Тесты для игрока.
 */
class PlayerTest {

    @Test
    void testGetHand() {
        Card twoSpades = new Card(Rank.TWO, Suit.SPADES);
        Player player = new Player();

        player.getHand().addCard(twoSpades);
        assertEquals("[Двойка Пики (2)]", player.getHand().toString());
    }

    @Test
    void testMakeMove() {
        Player player = new Player();
        Deck deck = new Deck(1);
        player.makeMove(deck);
        assertEquals(51, deck.getSize());
    }
}
