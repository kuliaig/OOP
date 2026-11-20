package org.example;

/**
 * Результат раунда.
 */
public enum RoundResult {
    PLAYER_WIN,
    DEALER_WIN,
    DRAW;

    /**
     * Проводит результат раунда (не считая блэкджек).
     *
     * @param player рука игрока
     * @param dealer рука дилера
     * @return реузльтат
     */
    public static RoundResult takeResult(Hand player, Hand dealer) {

        if (player.isBust()) {
            return RoundResult.DEALER_WIN;
        }
        if (dealer.isBust()) {
            return RoundResult.PLAYER_WIN;
        }
        if (player.getScore() > dealer.getScore()) {
            return RoundResult.PLAYER_WIN;
        }
        if (player.getScore() < dealer.getScore()) {
            return RoundResult.DEALER_WIN;
        }
        return RoundResult.DRAW;

    }
}

