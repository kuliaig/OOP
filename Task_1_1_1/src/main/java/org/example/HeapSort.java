package org.example;

/**
 * Пирамидальная сортировка
 * Сложность P(n*log(n))
 */
public class HeapSort
{
    /**
     * Функция для сортировки
     * Создает maxheap, убирает элемент в конец и так со всеми элементами
     * @param arr массив для сортировки
      */
    public static void heapsort(int[] arr)
    {
        int num = arr.length;
        for (int i = num / 2 - 1; i >= 0; i--) {
            siftdown(arr, i, num);
        }

        for (int i = num - 1; i > 0; i--)
        {
            // Берем максимум -> в конец
            swap(arr, 0, i);
            // Проверяем нет ли нарушений при перестановке
            siftdown(arr, 0, i);
        }
    }

    /**
     * Меняет местами два элемента массива по индексам i и j
     */
    private static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Проталкиваем элементы вниз для создания кучи
     * num - количество еще не отсортированных элементов
      */

    private static void siftdown(int[] arr, int i, int num)
    {
        int now = i;
        int left = i*2 + 1;
        int right = i*2 + 2;

        if (left < num && arr[left] > arr[now])
        {
            now = left;
        }

        if (right < num && arr[right] > arr[now])
        {
            now = right;
        }

        if (now != i)
        {
            swap(arr, now, i);
            siftdown(arr, now, num);
        }
    }
}
