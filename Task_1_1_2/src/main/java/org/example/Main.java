package org.example;

import java.io.PrintStream;

/**
 * Для запуска программы.
 */
public class Main {

    /**
     * Запускает игру Блэкджек.
     *
     * @param args аргументы командной строки (не используются)
     * @throws Exception если ошибка
     */
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        Game game = new Game(1);
        game.startGame();
    }
}