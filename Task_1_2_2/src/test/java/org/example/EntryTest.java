package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntryTest {
    Entry<String, Integer> entry;

    @BeforeEach
    void setUp() {
        entry = new Entry<>("hehe", 5);
    }

    @Test
    void getKey() {
        assertEquals("hehe", entry.getKey());
    }

    @Test
    void getValue() {
        assertEquals(5, entry.getValue());
    }

    @Test
    void testEquals() {
        Entry<String, Integer> entry2 = new Entry<>("hehe", 7);
        assertNotEquals(entry, entry2);
        entry2 = new Entry<>("haha", 5);
        assertNotEquals(entry, entry2);
        entry2 = new Entry<>("hehe", 5);
        assertEquals(entry, entry2);
        Entry<Integer, String> entry3 = new Entry<>(5, "hehe");
        assertNotEquals(entry, entry3);
    }

    @Test
    void testHashCode() {
        Entry<String, Integer> entry2 = new Entry<>("hehe", 5);
        assertEquals(entry.hashCode(), entry2.hashCode());
        Entry<Integer, String> entry3 = new Entry<>(5, "haha");
        assertNotEquals(entry.hashCode(), entry3.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("hehe = 5", entry.toString());
    }
}