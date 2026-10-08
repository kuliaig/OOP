package org.example;

import java.io.PrintStream;

/**
 * Точка входа программы.
 */
public class Main {

    /**
     * Запускает программу.
     *
     * @param args не ипсользуются
     */
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        OutInput.printProg();
    }
}
