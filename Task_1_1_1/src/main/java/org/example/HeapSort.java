package org.example;

/**
 * Пирамидальная сортировка.
 * Сложность O(n*log(n))
 */
public class HeapSort {
    /**
     * Функция для сортировки.
     * Создает maxheap, убирает элемент в конец и так со всеми элементами.
     *
     * @param arr массив для сортировки
     */
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(arr, i, n);
        }

        for (int i = n - 1; i > 0; i--) {
            // Берем максимум -> в конец
            swap(arr, 0, i);
            // Проверяем нет ли нарушений при перестановке
            siftDown(arr, 0, i);
        }
    }

    /**
     * Меняет местами два элемента массива по индексам i и j.
     *
     * @param arr массив
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Проталкиваем элементы вниз для создания кучи.
     *
     * @param arr массив
     * @param i индекс элемента
     * @param unsorted количество еще не отсортированных элементов
     */
    private static void siftDown(int[] arr, int i, int unsorted) {
        int now = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;

        if (left < unsorted && arr[left] > arr[now]) {
            now = left;
        }

        if (right < unsorted && arr[right] > arr[now]) {
            now = right;
        }

        if (now != i) {
            swap(arr, now, i);
            siftDown(arr, now, unsorted);
        }
    }
}
