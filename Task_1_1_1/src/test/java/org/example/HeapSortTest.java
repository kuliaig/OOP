package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Тесты для HeapSort.
 */
class HeapSortTest {

    @Test
    void sort_empty() {
        int[] arr = {};
        int[] result = {};
        HeapSort.sort(arr);
        assertArrayEquals(result, arr);
    }

    @Test
    void sort_usual() {
        int[] arr = {3, 4, 1, 2, 8, 9};
        int[] result = {1, 2, 3, 4, 8, 9};
        HeapSort.sort(arr);
        assertArrayEquals(result, arr);
    }

    @Test
    void sort_sort() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] result = {1, 2, 3, 4, 5};
        HeapSort.sort(arr);
        assertArrayEquals(result, arr);
    }

    @Test
    void sort_unsort() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] result = {1, 2, 3, 4, 5};
        HeapSort.sort(arr);
        assertArrayEquals(result, arr);
    }
}
