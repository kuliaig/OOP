package org.example;

/**
 * Масть карты и ее русское название.
 */
enum Suit {
    SPADES("Пики"),
    HEARTS("Червы"),
    DIAMONDS("Бубны"),
    CLUBS("Трефы");

    private final String russianName;

    Suit(String russianName) {
        this.russianName = russianName;
    }

    /**
     * Возвращает русское название масти.
     *
     * @return название
     */
    @Override
    public String toString() {
        return russianName;
    }
}
