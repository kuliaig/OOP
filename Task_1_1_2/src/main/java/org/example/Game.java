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
            OutInput.startRound(roundCount);
            playRound();

            if (OutInput.askNextRound() == 0) {
                break;
            }
        }
    }

    private void playRound() {
        user.getHand().clear();
        dealer.getHand().clear();

        user.getHand().takeCard(deck);
        dealer.getHand().takeCard(deck);
        user.getHand().takeCard(deck);

        Card closedCard = dealer.getHand().takeCard(deck);
        closedCard.setOpen(false);

        OutInput.giveCards();
        OutInput.usersCards(user);
        OutInput.dealersCards(dealer, false);
        OutInput.printEmpty();

        int userScore = user.getHand().getScore();
        int take;
        if (userScore == 21) {
            OutInput.sayWin();
            take = 0;
        }  else {
            OutInput.yourMove();
            take = OutInput.askTakeCard();
        }

        boolean stop = false;

        while (take == 1) {
            checkDeck();
            Card newCard = user.makeMove(deck);
            OutInput.youOpen(newCard);

            OutInput.usersCards(user);
            OutInput.dealersCards(dealer, false);
            OutInput.printEmpty();

            userScore = user.getHand().getScore();
            if (userScore > 21) {
                OutInput.sayTooMuch();
                stop = true;
                break;
            } else if (userScore == 21) {
                OutInput.sayWin();
                break;
            }
            take = OutInput.askTakeCard();
        }

        OutInput.dealersMove();
        closedCard.setOpen(true);
        OutInput.dealerOpenClosed(closedCard);
        OutInput.usersCards(user);
        OutInput.dealersCards(dealer, true);
        OutInput.printEmpty();

        if (stop == false) {
            while (dealer.getHand().getScore() < 17) {
                checkDeck();
                Card newCard = dealer.makeMove(deck);
                OutInput.dealerOpen(newCard);

                OutInput.usersCards(user);
                OutInput.dealersCards(dealer, true);
                OutInput.printEmpty();
            }
        }

        userScore = user.getHand().getScore();
        int dealerScore = dealer.getHand().getScore();

        if (userScore > 21) {

            dealersWin++;

            OutInput.endRound(false, playersWin, dealersWin);

        } else if (userScore == 21) {

            playersWin++;
            if (dealerScore == 21) {
                dealersWin++;
            }
            OutInput.endRound(true, playersWin, dealersWin);

        } else {

            if (dealerScore > 21 || dealerScore < userScore) {
                playersWin++;
                OutInput.endRound(true, playersWin, dealersWin);
            } else if (dealerScore == userScore) {
                OutInput.endRound(false, playersWin, dealersWin);
            } else {
                dealersWin++;
                OutInput.endRound(false, playersWin, dealersWin);
            }
        }
    }
}
