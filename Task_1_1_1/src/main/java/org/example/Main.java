package org.example;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Проверяет работу сортировки, можно ввести свой массив.
 */
public class Main {
    /**
     * Сортирует массив.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Вход: ");
        String line = scanner.nextLine();
        scanner.close();
        String[] stringArr = line.split(" ");
        int[] arr = new int[stringArr.length];
        for (int i = 0; i < stringArr.length; i++) {
            arr[i] = Integer.parseInt(stringArr[i]);
        }
        HeapSort.sort(arr);
        System.out.println("Выход: " + Arrays.toString(arr));
    }
}