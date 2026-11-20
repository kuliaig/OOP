package org.example;

/**
 * Игра.
 */
public class Game {

    private Deck deck;
    private final Player user = new Player();
    private final Player dealer = new Player();

    private int playersWin = 0;
    private int dealersWin = 0;
    private int roundCount = 0;
    private int deckCount;

    /**
     * Создаёт игру.
     *
     * @param deckCount количество колод
     */
    public Game(int deckCount) {
        this.deckCount = deckCount;
        this.deck = new Deck(deckCount);
    }

    /**
     * Создаёт игру с готовой колодой (для тестов).
     *
     * @param deck готовая колода
     * @param deckCount количество колод
     */
    Game(Deck deck, int deckCount) {
        this.deck = deck;
        this.deckCount = deckCount;
    }

    private void checkDeck() {
        if (deck.getSize() == 0) {
            deck = new Deck(deckCount);
            OutInput.printNewDeck(deckCount);
        }
    }

    /**
     * Начинает игру.
     */
    public void startGame() {

        OutInput.sayHello();

        while (true) {
            if (deck.getSize() < 15) {
                deck = new Deck(deckCount);
                OutInput.printNewDeck(deckCount);
            }

            roundCount++;
            OutInput.sayStartRound(roundCount);
            playRound();

            if (OutInput.askNextRound() == 0) {
                break;
            }
        }
    }

    private void showHands(boolean isOpen) {
        OutInput.usersCards(user);
        OutInput.dealersCards(dealer, isOpen);
        OutInput.printEmpty();
    }

    /**
     * Количество побед игрока.
     *
     * @return победы игрока
     */
    int getPlayersWin() {
        return playersWin;
    }

    /**
     * Количество побед дилера.
     *
     * @return победы дилера
     */
    int getDealersWin() {
        return dealersWin;
    }

    private Card startRound() {
        user.getHand().clear();
        dealer.getHand().clear();

        user.getHand().takeCard(deck);
        dealer.getHand().takeCard(deck);
        user.getHand().takeCard(deck);

        Card closedCard = dealer.getHand().takeCard(deck);
        closedCard.setOpen(false);

        OutInput.sayGiveCards();
        showHands(false);

        return closedCard;
    }

    private RoundResult checkBlack() {
        RoundResult result;
        if (user.getHand().isBlackjack()) {
            if (dealer.getHand().isBlackjack()) {
                result = RoundResult.DRAW;
                OutInput.bothBlack();
            } else {
                playersWin++;
                result = RoundResult.PLAYER_WIN;
                OutInput.userBlack();
            }

        } else {
            dealersWin++;
            OutInput.dealerBlack();
            result = RoundResult.DEALER_WIN;
        }
        OutInput.printEmpty();
        return result;
    }

    private boolean usersMove(int take) {
        boolean stop = false;
        while (take == 1) {
            checkDeck();
            Card newCard = user.makeMove(deck);
            OutInput.youOpen(newCard);

            showHands(false);

            if (user.getHand().isBust()) {
                OutInput.sayTooMuch();
                stop = true;
                break;
            } else if (user.getHand().isTwentyOne()) {
                OutInput.sayWin();
                break;
            }
            take = OutInput.askTakeCard();
        }
        return stop;
    }

    private void dealersMove() {
        while (dealer.getHand().getScore() < 17) {
            checkDeck();
            Card newCard = dealer.makeMove(deck);
            OutInput.dealerOpen(newCard);

            showHands(true);
        }
    }

    private void endRound(RoundResult result) {
        switch (result) {
            case PLAYER_WIN:
                playersWin++;
                break;
            case DEALER_WIN:
                dealersWin++;
                break;
            default:
                break;
        }
        OutInput.printEndRound(result, playersWin, dealersWin);
    }

    /**
     * Один раунд игры.
     *
     * @return результат раунда (для тестов)
     */
    RoundResult playRound() {

        Card closedCard = startRound();

        if (user.getHand().isBlackjack() || dealer.getHand().isBlackjack()) {
            final RoundResult result = checkBlack();

            closedCard.setOpen(true);
            OutInput.dealerOpenClosed(closedCard);
            showHands(true);

            OutInput.printEndRound(result, playersWin, dealersWin);
            return result;
        }

        OutInput.printYourMove();
        int take = OutInput.askTakeCard();
        final boolean stop = usersMove(take);

        OutInput.printDealersMove();
        closedCard.setOpen(true);
        OutInput.dealerOpenClosed(closedCard);
        showHands(true);

        if (!stop) {
            dealersMove();
        }

        RoundResult result = RoundResult.takeResult(user.getHand(), dealer.getHand());
        endRound(result);
        return result;
    }
}
