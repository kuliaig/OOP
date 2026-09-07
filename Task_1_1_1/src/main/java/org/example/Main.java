package org.example;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Проверяет работу сортировки, можно ввести свой массив
 */
public class Main {
    /**
     * Сортирует массив
     *
     * @param args элементы массива
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Вход: ");
        String line = scanner.nextLine();
        scanner.close();
        String[] string_arr= line.split(" ");
        int[] arr= new int[string_arr.length];
        for (int i = 0; i < string_arr.length; i++) {
            arr[i] = Integer.parseInt(string_arr[i]);
        }
        HeapSort.heapsort(arr);
        System.out.println("Выход: " + java.util.Arrays.toString(arr));
    }
}