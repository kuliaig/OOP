package org.example;

/**
 * Игрок.
 */
public class Player {

    private final Hand hand = new Hand();

    /**
     * Возвращает руку.
     *
     * @return рука
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Игрок делает ход.
     *
     * @param deck колода
     */
    public Card makeMove(Deck deck) {
        Card card = hand.takeCard(deck);
        return card;
    }
}
