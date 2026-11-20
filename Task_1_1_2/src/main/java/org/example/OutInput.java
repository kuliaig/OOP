package org.example;

import java.util.Scanner;

/**
 * Ввод и вывод.
 */
public class OutInput {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Сбрасывает сканер.
     */
    static void resetScanner() {
        scanner = new Scanner(System.in);
    }

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
    public static void sayStartRound(int countRound) {
        System.out.println("Раунд " + countRound);
    }

    /**
     * Выводит сообщение о раздаче карт.
     */
    public static void sayGiveCards() {
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
     * Выводит пустую строку.
     */
    public static void printEmpty() {
        System.out.println(" ");
    }

    /**
     * Вывод надписи Ваш ход.
     */
    public static void printYourMove() {
        System.out.println("Ваш ход");
        System.out.println("-------");
    }

    /**
     * Вывод надписи Ход дилера.
     */
    public static void printDealersMove() {
        System.out.println("Ход дилера");
        System.out.println("-------");
    }

    /**
     * Вывод в конце раунда.
     *
     * @param result результат раунда
     * @param usersScore счет пользователя (сколько раундов выиграл)
     * @param dealersScore счет дилера
     */
    public static void printEndRound(RoundResult result, int usersScore, int dealersScore) {
        switch (result) {
            case PLAYER_WIN:
                System.out.println("Вы выиграли раунд! Счёт " + usersScore + ":"
                        + dealersScore + " в вашу пользу.");
                break;
            case DEALER_WIN:
                System.out.println("Вы проиграли раунд! Счёт " + usersScore + ":"
                        + dealersScore + " в пользу дилера.");
                break;
            case DRAW:
                System.out.println("Ничья! Счёт " + usersScore + ":" + dealersScore);
                break;
            default:
                break;
        }
        System.out.println();
    }

    /**
     * Выводит сообщение о переборе.
     */
    public static void sayTooMuch() {
        System.out.println("Вы взяли слишком много карт, перебор :(");
        printEmpty();
    }

    /**
     * Выводит сообщение о наборе 21.
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
        while (true) {
            System.out.println("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice == 0 || choice == 1) {
                    return choice;
                }
            } else {
                scanner.next();
            }

            System.out.println("Ошибка :( Вы ввели неправильный символ.");
        }
    }

    /**
     * Спрашивает, хочет ли пользователь продолжить игру.
     *
     * @return 1 если хочет, иначе 0
     */
    public static int askNextRound() {
        while (true) {
            System.out.println("Введите \"1\", чтобы продолжить игру, и \"0\", "
                    + "чтобы остановиться...");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice == 0 || choice == 1) {
                    return choice;
                }
            } else {
                scanner.next();
            }

            System.out.println("Ошибка :( Вы ввели неправильный символ.");
        }
    }

    /**
     * Выводит сообщение "Вы открыли карту ...".
     */
    public static void youOpen(Card card) {
        System.out.println("Вы открыли карту " + card.toRussian(false));
    }

    /**
     * Выводит сообщение "Дилер открывает закрытую карту ...".
     */
    public static void dealerOpenClosed(Card card) {
        System.out.println("Дилер открывает закрытую карту " + card.toRussian(false));
    }

    /**
     * Выводит сообщение "Дилер открывает карту ...".
     */
    public static void dealerOpen(Card card) {
        System.out.println("Дилер открывает карту " + card.toRussian(false));
    }

    /**
     * Выводит сообщение "У дилера блэкджек, ты проиграл :(".
     */
    public static void dealerBlack() {
        System.out.println("У дилера блэкджек, вы проиграли :(");
    }

    /**
     * Выводит сообщение "Поздравляю, у вас блэкджек!".
     */
    public static void userBlack() {
        System.out.println("Поздравляю, у вас блэкджек!");
    }

    /**
     * Выводит сообщение "И у вас и у дилера блэкджек, ничья D:".
     */
    public static void bothBlack() {
        System.out.println("И у вас и у дилера блэкджек, ничья D:");
    }
}
