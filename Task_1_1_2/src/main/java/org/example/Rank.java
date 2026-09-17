package org.example;

/**
 * Ранг карты, ее имя и значение
 */
enum Rank {TWO(2, "Двойка"), THREE(3, "Тройка"), FOUR(4, "Четверка"),
    FIVE(5, "Пятерка"), SIX(6, "Шестерка"), SEVEN(7, "Семерка"),
    EIGHT(8, "Восьмерка"), NINE(9, "Девятка"), TEN(10, "Десятка"),
    JACK(10, "Валет"), QUEEN(10, "Дама"),
    KING(10, "Король"), ACE(11, "Туз");

    private final int value;
    private final String russianName;

    Rank(int value, String russianName) {
        this.value = value;
        this.russianName = russianName;
    }

    /**
     * Возвращает значение ранга в числах.
     *
     * @return значение
     */
    public int getValue() {
        return value;
    }

    /**
     * Возвращает русское название ранга.
     *
     * @return название
     */
    @Override
    public String toString() {
        return russianName;
    }
}