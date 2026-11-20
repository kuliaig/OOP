package org.example;

/**
 * Карта.
 */
public class Card {

    private final Rank rank;
    private final Suit suit;
    private boolean isOpen;

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.isOpen = true;
    }

    /**
     * Устанавливает, открыта карта или закрыта.
     *
     * @param isOpen состояние карты
     */
    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * Возвращает значение карты в очках.
     *
     * @return значение в очках
     */
    public int getValue() {
        return rank.getValue();
    }

    /**
     * Возвращает ранг карты.
     *
     * @return ранг карты
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Возвращает масть карты.
     *
     * @return масть
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Возвращает открыта карта или закрыта.
     *
     * @return открыта карта или закрыта (true or false)
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Переводит значение карты в строку.
     *
     * @return ранг масть (значение)
     */
    public String toRussian(boolean isAce1) {
        if (!isOpen) {
            return "<закрытая карта>";
        }
        if (isAce1) {
            return rank + " " + suit + " (1)";
        }
        return rank + " " + suit + " (" + getValue() + ")";
    }
}
