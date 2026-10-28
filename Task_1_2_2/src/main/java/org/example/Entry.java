package org.example;

import java.util.Objects;

/**
 * Пара ключ-значение.
 *
 * @param <Key> ключ
 * @param <Value> значение
 */
public class Entry<Key, Value> {

    private final Key key;
    private final Value value;

    /**
     * Конструктор.
     *
     * @param key ключ
     * @param value значение
     */
    public Entry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Возвращает ключ.
     *
     * @return ключ
     */
    public Key getKey() {
        return key;
    }

    /**
     * Возвращает значение.
     *
     * @return значение
     */
    public Value getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Entry<?, ?> other)) {
            return false;
        }
        return Objects.equals(key, other.key) && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }
}