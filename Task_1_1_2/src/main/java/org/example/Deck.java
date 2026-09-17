package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Колода.
 */
public class Deck {

    private final List<Card> decks = new ArrayList<>();

    /**
     * Создание новой колоды.
     *
     * @param deckCount количество колод в одну
     */
    public Deck(int deckCount) {
        for (int i = 0; i < deckCount; i++) {
            for (Rank rank: Rank.values()) {
                for (Suit suit: Suit.values()) {
                    decks.add(new Card(rank, suit));
                }
            }
        }
        Collections.shuffle(decks);
    }

    /**
     * Размер колоды в данный момент
     *
     * @return количество оставшихся в колоде карт
     */
    public int getSize() {
        return decks.size();
    }


    public Card getCard() {
        if (decks.isEmpty()) {
            throw new IllegalStateException("Колода закончилась");
        }
        return decks.remove(decks.size() - 1);
    }
}
