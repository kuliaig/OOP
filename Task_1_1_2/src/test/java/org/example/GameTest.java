package org.example;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для игры.
 */
class GameTest {

    private Card five;
    private Card seven;
    private Card ten;
    private Card jack;
    private Card king;
    private Card ace;

    @BeforeEach
    void set() {
        five = new Card(Rank.FIVE, Suit.SPADES);
        seven = new Card(Rank.SEVEN, Suit.HEARTS);
        ten = new Card(Rank.TEN, Suit.DIAMONDS);
        jack = new Card(Rank.JACK, Suit.SPADES);
        king = new Card(Rank.KING, Suit.HEARTS);
        ace = new Card(Rank.ACE, Suit.CLUBS);
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        OutInput.resetScanner();
    }

    @Test
    void testUserBlack() {
        Deck deck = new Deck(0);
        deck.addCard(ten);
        deck.addCard(jack);
        deck.addCard(seven);
        deck.addCard(ace);

        Game game = new Game(deck, 1);
        setInput("0\n");

        RoundResult result = game.playRound();
        assertEquals(RoundResult.PLAYER_WIN, result);
    }

    @Test
    void testDealerBlack() {
        Deck deck = new Deck(0);
        deck.addCard(jack);
        deck.addCard(ten);
        deck.addCard(ace);
        deck.addCard(seven);

        Game game = new Game(deck, 1);
        setInput("0\n");

        RoundResult result = game.playRound();
        assertEquals(RoundResult.DEALER_WIN, result);
    }

    @Test
    void testBothBlack() {
        Deck deck = new Deck(0);
        deck.addCard(jack);
        deck.addCard(king);
        deck.addCard(ace);
        deck.addCard(ace);

        Game game = new Game(deck, 1);
        setInput("0\n");

        RoundResult result = game.playRound();
        assertEquals(RoundResult.DRAW, result);
    }

    @Test
    void testTooMuchUser() {
        Deck deck = new Deck(0);
        deck.addCard(ten);
        deck.addCard(jack);
        deck.addCard(ten);
        deck.addCard(five);
        deck.addCard(ten);

        Game game = new Game(deck, 1);
        setInput("1\n");

        RoundResult result = game.playRound();
        assertEquals(RoundResult.DEALER_WIN, result);
    }

    @Test
    void testTooMuchDealer() {
        Deck deck = new Deck(0);
        deck.addCard(ten);
        deck.addCard(jack);
        deck.addCard(ten);
        deck.addCard(five);
        deck.addCard(ten);

        Game game = new Game(deck, 1);
        setInput("0\n");

        RoundResult result = game.playRound();
        assertEquals(RoundResult.PLAYER_WIN, result);
    }

    @Test
    void testDraw() {
        Deck deck = new Deck(0);
        deck.addCard(five);
        deck.addCard(jack);
        deck.addCard(ten);
        deck.addCard(five);
        deck.addCard(ten);

        Game game = new Game(deck, 1);
        setInput("0\n");

        RoundResult result = game.playRound();
        assertEquals(RoundResult.DRAW, result);
    }

    @Test
    void testWinScore() {
        Deck deck = new Deck(0);
        deck.addCard(five);
        deck.addCard(seven);
        deck.addCard(ten);
        deck.addCard(ten);
        deck.addCard(five);

        Game game = new Game(deck, 1);
        setInput("1\n0\n");

        RoundResult result = game.playRound();
        assertEquals(RoundResult.PLAYER_WIN, result);
    }

    @Test
    void testStartGame() {
        Deck deck = new Deck(0);

        for (int i = 0; i < 11; i++) {
            deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
        }

        deck.addCard(new Card(Rank.SIX, Suit.HEARTS));
        deck.addCard(new Card(Rank.KING, Suit.HEARTS));
        deck.addCard(new Card(Rank.FIVE, Suit.SPADES));
        deck.addCard(new Card(Rank.ACE, Suit.SPADES));

        setInput("0\n0\n");

        Game game = new Game(deck, 1);
        game.startGame();

        assertEquals(1, game.getPlayersWin());
        assertEquals(0, game.getDealersWin());
    }
}
