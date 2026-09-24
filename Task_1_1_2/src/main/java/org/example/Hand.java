package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Рука (держит и берет карты).
 */
public class Hand {

    private final List<Card> cards = new ArrayList<>();

    /**
     * Рука берет карту (для тестов).
     *
     * @param card карта
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Рука берет карту из колоды.
     *
     * @param deck колода
     */
    public Card takeCard(Deck deck) {
        Card card = deck.getCard();
        cards.add(card);
        return card;
    }

    /**
     * Возвращает сумму очков на руке.
     *
     * @return сумма очков
     */
    public int getScore() {
        int sum = 0;
        int countAce = 0;

        for (Card card : cards) {
           sum += card.getValue();
           if (card.getRank() == Rank.ACE) {
               countAce++;
           }
        }

        while (sum > 21 && countAce > 0) {
            sum -= 10;
            countAce--;
        }

        return sum;
    }

    /**
     * Проверяет, блэкджек у игрока или нет.
     *
     * @return true если да иначе false
     */
    public boolean isBlackjack() {
        return cards.size() == 2 && getScore() == 21;
    }

    /**
     * Проверяет, перебор или нет.
     *
     * @return true, если перебор
     */
    public boolean isBust() {
        return getScore() > 21;
    }

    /**
     * Открывает все карты (для дилера).
     */
    public void openAllCards() {
        for (Card card : cards) {
            card.setOpen(true);
        }
    }

    /**
     * Проверяет, равна ли сумма 21.
     *
     * @return true, если 21
     */
    public boolean isTwentyOne() {
        return getScore() == 21;
    }

    /**
     * Очищает руку.
     */
    public void clear() {
        cards.clear();
    }

    /**
     * Возвращает все карты в руке в строковой форме.
     *
     * @return [карта, карта,.. карта]
     */
    @Override
    public String toString() {
        String out = "[";

        int sum = 0;
        int count = 0;
        for (Card card : cards) {
            if (card.isOpen()) {
                sum += card.getValue();
            }
            count++;
        }

        int countAce = (sum - getScore()) / 10;

        for (Card card : cards) {
            if (card.getRank() == Rank.ACE && countAce > 0) {
                out += card.toRussian(true);
            } else {
                out += card.toRussian(false);
            }

            if (count > 1) {
                out += ", ";
            }
            count--;
        }
        out += "]";
        return out;
    }
}

