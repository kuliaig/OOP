package org.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {
    HashTable<String, Integer> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
        table.put("hehe", 5);
    }

    @Test
    void put() {
        assertTrue(table.contains("hehe"));
    }

    @Test
    void remove() {
        table.remove("hehe", 5);
        assertFalse(table.contains("hehe"));
    }

    @Test
    void removeMissingKey() {
        table.remove("nope", 1);
        assertTrue(table.contains("hehe"));
    }

    @Test
    void get() {
        assertEquals(5, table.get("hehe"));
    }

    @Test
    void update() {
        table.update("hehe", 3);
        assertEquals(3, table.get("hehe"));
    }

    @Test
    void iterator() {
        HashTable<String, Integer> table2 = new HashTable<>();
        table2.put("a", 1);
        table2.put("b", 2);
        table2.put("c", 3);

        Set<String> keys = new HashSet<>();
        for (Entry<String, Integer> e : table2) {
            keys.add(e.getKey());
        }
        assertEquals(Set.of("a", "b", "c"), keys);
    }

    @Test
    void testEquals() {
        HashTable<Integer, String> table2 = new HashTable<>();
        table2.put(5, "hehe");

        assertNotEquals(table, table2);

        HashTable<String, Integer> table3 = new HashTable<>();
        assertNotEquals(table, table3);
        table3.put("hehe", 5);
        assertEquals(table, table3);
    }

    @Test
    void testHashCode() {
        HashTable<String, Integer> table2 = new HashTable<>();
        table2.put("hehe", 5);

        assertEquals(table.hashCode(), table2.hashCode());
    }

    @Test
    void iteratorThrows() {
        table.put("second", 2);
        assertThrows(ConcurrentModificationException.class, () -> {
            for (Entry<String, Integer> e : table) {
                table.remove(e.getKey(), e.getValue());
            }
        });
    }

    @Test
    void iteratorDoesNotThrow() {
        table.put("second", 2);
        assertDoesNotThrow(() -> {
            for (Entry<String, Integer> e : table) {
                table.update(e.getKey(), 100);
            }
        });
    }

    @Test
    void resize() {
        HashTable<Integer, Integer> big = new HashTable<>();
        for (int i = 0; i < 100; i++) {
            big.put(i, i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(i, big.get(i));
        }
    }

    @Test
    void testToString() {
        assertEquals("{hehe = 5}", table.toString());
    }

    @Test
    void updateMissingKey() {
        assertThrows(NoSuchElementException.class, () -> table.update("nope", 1));
    }
}