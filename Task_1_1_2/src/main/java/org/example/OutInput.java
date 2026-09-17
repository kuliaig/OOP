package org.example;

import java.util.Scanner;

/**
 * Ввод и вывод.
 */
public class OutInput {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Приветствие пользователя.
     */
    public static void sayHello() {
        System.out.println("Добро пожаловать в Блэкджек!");
    }

    /**
     * Начало нового раунда.
     *
     * @param countRound номер раунда
     */
    public static void startRound(int countRound) {
        System.out.println("Раунд " + countRound);
    }

    /**
     * Выводит сообщение о раздаче карт.
     */
    public static void giveCards() {
        System.out.println("Дилер раздал карты");
    }

    /**
     * Вывод карт пользователя.
     *
     * @param player пользователь
     */
    public static void usersCards(Player player) {
        Hand hand = player.getHand();
        System.out.println("Ваши карты: " + hand + " => " + hand.getScore());
    }

    /**
     * Вывод карт дилера.
     *
     * @param dealer дилер
     * @param isOpen открыты ли карты (от этого зависит, нужно ли
     *               выводить общее число)
     */
    public static void dealersCards(Player dealer, boolean isOpen) {
        Hand hand = dealer.getHand();
        if (!isOpen) {
            System.out.println("Карты дилера: " + hand);
        } else {
            System.out.println("Карты дилера: " + hand + " => " + hand.getScore());
        }
    }

    /**
     * Выводит пустую строку
     */
    public static void printEmpty() {
        System.out.println(" ");
    }

    /**
     * Вывод надписи Ваш ход.
     */
    public static void yourMove() {
        System.out.println("Ваш ход");
        System.out.println("-------");
    }

    /**
     * Вывод надписи Ход дилера
     */
    public static void dealersMove() {
        System.out.println("Ход дилера");
        System.out.println("-------");
    }

    /**
     * Вывод в конце раунда.
     *
     * @param usersWin выиграл ли пользователь
     * @param usersScore счет пользователя (сколько раундов выиграл)
     * @param dealersScore счет дилера
     */
    public static void endRound(boolean usersWin, int usersScore, int dealersScore) {
        if (usersWin) {
            if (usersScore > dealersScore) {
                System.out.println("Вы выиграли раунд! Счет " + usersScore + ":" +
                        dealersScore + " в вашу пользу.");
                System.out.println(" ");
            } else if (usersScore == dealersScore) {
                System.out.println("Вы выиграли раунд! Счет " + usersScore + ":" +
                        dealersScore + ", ничья.");
                System.out.println(" ");
            } else {
                System.out.println("Вы выиграли раунд! Счет " + usersScore + ":" +
                        dealersScore + " в пользу дилера.");
                System.out.println(" ");
            }
        } else {
            if (usersScore > dealersScore) {
                System.out.println("Вы проиграли раунд! Счет " + usersScore + ":" +
                        dealersScore + " в вашу пользу.");
                System.out.println(" ");
            } else if (usersScore == dealersScore) {
                System.out.println("Вы проиграли раунд! Счет " + usersScore + ":" +
                        dealersScore + ", ничья.");
                System.out.println(" ");
            } else {
                System.out.println("Вы проиграли раунд! Счет " + usersScore + ":" +
                        dealersScore + " в пользу дилера.");
                System.out.println(" ");
            }
        }
    }

    /**
     * Выводит сообщение о переборе
     */
    public static void sayTooMuch() {
        System.out.println("Вы взяли слишком много карт, перебор :(");
    }

    /**
     * Выводит сообщение о наборе 21
     */
    public static void sayWin() {
        System.out.println("Поздравляю! Вы набрали 21 :)");
    }

    /**
     * Сообщение о новой колоде.
     *
     * @param deckCount количество колод
     */
    public static void printNewDeck(int deckCount) {
        System.out.println("Новая колода из " + deckCount + " колод");
    }

    /**
     * Спрашивает у игрока, брать следующую карту или нет.
     *
     * @return 0 или 1
     */
    public static int askTakeCard() {
        while(true) {
            System.out.println("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...");

            if (SCANNER.hasNextInt()) {
                int choice = SCANNER.nextInt();
                if (choice == 0 || choice == 1) {
                    return choice;
                }
            } else {
                SCANNER.next();
            }

            System.out.println("Ошибка :( Вы ввели неправильный символ.");
        }
    }

    public static int askNextRound() {
        while (true) {
            System.out.println("Введите \"1\", чтобы продолжить игру, и \"0\", чтобы остановиться...");

            if (SCANNER.hasNextInt()) {
                int choice = SCANNER.nextInt();
                if (choice == 0 || choice == 1) {
                    return choice;
                }
            } else {
                SCANNER.next();
            }

            System.out.println("Ошибка :( Вы ввели неправильный символ.");
        }
    }

    /**
     * Выводит сообщение "Вы открыли карту ..."
     */
    public static void youOpen(Card card) {
        System.out.println("Вы открыли карту " + card.toRussian(false));
    }

    /**
     * Выводит сообщение "Дилер открывает закрытую карту ..."
     */
    public static void dealerOpenClosed(Card card) {
        System.out.println("Дилер открывает закрытую карту " + card.toRussian(false));
    }

    /**
     * Выводит сообщение "Дилер открывает карту ..."
     */
    public static void dealerOpen(Card card) {
        System.out.println("Дилер открывает карту " + card.toRussian(false));
    }
}
