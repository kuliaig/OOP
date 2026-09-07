package org.example;

import java.util.Arrays;

/**
 * Проверяет работу сортировки на примере из задачи
 */
public class Main {
    /**
     * Выводит пример из условия задачи
     * @param args не используется
     */
    public static void main(String[] args)
    {
        int[] arr= {5, 4, 3, 2, 1};
        System.out.println("Вход: " + java.util.Arrays.toString(arr));
        HeapSort.heapsort(arr);
        System.out.println("Выход: " + java.util.Arrays.toString(arr));
    }
}
