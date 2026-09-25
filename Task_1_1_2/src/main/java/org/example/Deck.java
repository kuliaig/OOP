package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            for (Rank rank : Rank.values()) {
                for (Suit suit : Suit.values()) {
                    decks.add(new Card(rank, suit));
                }
            }
        }
        Collections.shuffle(decks);
    }

    void addCard(Card card) {
        decks.add(card);
    }

    /**
     * Размер колоды в данный момент.
     *
     * @return количество оставшихся в колоде карт
     */
    public int getSize() {
        return decks.size();
    }

    /**
     * Дает карту.
     *
     * @return карта
     */
    public Card getCard() {
        return decks.remove(decks.size() - 1);
    }
}
