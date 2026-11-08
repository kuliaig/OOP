package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Хэш-таблица.
 *
 * @param <Key> ключ
 * @param <Value> значение
 */
public class HashTable<Key, Value> implements Iterable<Entry<Key, Value>> {

    private static final double LOAD_FACTOR = 0.75;
    private int capacity;
    private int size;
    private int modCount;

    private Node<Key, Value>[] chains;

    /**
     * Конструктор.
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        this.capacity = 16;
        this.size = 0;
        this.chains = (Node<Key, Value>[]) new Node[capacity];
        this.modCount = 0;
    }

    /**
     * Положить новый узел в Хэш-таблицу.
     *
     * @param key ключ
     * @param value значение
     */
    public void put(Key key, Value value) {
        Node<Key, Value> node = getNode(key);
        if (node != null) {
            node.changeValue(value);
            return;
        }

        int index = hash(key);
        Node<Key, Value> newNode = new Node<>(key, value);
        newNode.setNext(chains[index]);

        chains[index] = newNode;
        size++;
        modCount++;
        if ((double) size / capacity > LOAD_FACTOR) {
            resize();
        }
    }

    /**
     * Удалить узел из Хэш-таблицы.
     *
     * @param key ключ
     * @param value значение
     */
    public void remove(Key key, Value value) {
        int index = hash(key);
        Node<Key, Value> current = chains[index];

        if (current == null) {
            return;
        }

        if (Objects.equals(current.getKey(), key)
                && Objects.equals(current.getValue(), value)) {
            chains[index] = current.getNext();
            modCount++;
            size--;
            return;
        }

        Node<Key, Value> next = current.getNext();

        while (next != null) {
            if (Objects.equals(next.getKey(), key)
                    && Objects.equals(next.getValue(), value)) {
                current.setNext(next.getNext());
                modCount++;
                size--;
                return;
            }
            current = next;
            next = next.getNext();
        }
    }

    /**
     * Поиск значения по ключу.
     *
     * @param key ключ
     * @return значение или null, если не существует
     */
    public Value get(Key key) {
        Node<Key, Value> node = getNode(key);
        return node == null ? null : node.getValue();
    }

    /**
     * Обновляет значение по ключу.
     *
     * @param key ключ
     * @param value новое значение
     * @throws NoSuchElementException если ключа нет в таблице
     */
    public void update(Key key, Value value) {
        Node<Key, Value> node = getNode(key);
        if (node == null) {
            throw new NoSuchElementException("Ключ не найден: " + key);
        }
        node.changeValue(value);
    }

    /**
     * Проверяет, существует ли значение с таким ключом.
     *
     * @param key ключ
     * @return true если содержит иначе false
     */
    public boolean contains(Key key) {
        Node<Key, Value> node = getNode(key);
        return node != null;
    }

    @Override
    public Iterator<Entry<Key, Value>> iterator() {
        return new HashTableIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HashTable<?, ?> other)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        HashTable<Key, Value> otherTable = (HashTable<Key, Value>) other;

        if (size != otherTable.size) {
            return false;
        }

        for (Entry<Key, Value> entry : this) {
            if (!otherTable.contains(entry.getKey())
                    || !Objects.equals(entry.getValue(), otherTable.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (Entry<Key, Value> entry : this) {
            result += Objects.hashCode(entry.getKey()) ^ Objects.hashCode(entry.getValue());
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        boolean first = true;
        for (Entry<Key, Value> entry : this) {
            if (!first) {
                result.append(", ");
            }
            result.append(entry);
            first = false;
        }

        result.append("}");
        return result.toString();
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int oldCapacity = capacity;
        this.capacity = capacity * 2;

        Node<Key, Value>[] newChains = (Node<Key, Value>[]) new Node[capacity];

        for (int i = 0; i < oldCapacity; i++) {
            Node<Key, Value> cur = chains[i];

            while (cur != null) {
                Node<Key, Value> next = cur.getNext();

                int index = hash(cur.getKey());

                cur.setNext(newChains[index]);
                newChains[index] = cur;

                cur = next;
            }
        }

        this.chains = newChains;
        modCount++;
    }

    private int hash(Key key) {
        if (key == null) {
            return 0;
        }

        int hash = key.hashCode();
        hash = hash ^ (hash >>> 16);
        return (capacity - 1) & hash;
    }

    private Node<Key, Value> getNode(Key key) {
        int index = hash(key);
        Node<Key, Value> current = chains[index];
        while (current != null) {
            if (Objects.equals(current.getKey(), key)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    private class HashTableIterator implements Iterator<Entry<Key, Value>> {
        private final int expectedModCount = modCount;
        private int curIndex;
        private Node<Key, Value> nextNode;

        HashTableIterator() {
            move();
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public Entry<Key, Value> next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (nextNode == null) {
                throw new NoSuchElementException();
            }

            Entry<Key, Value> entry = new Entry<>(nextNode.getKey(), nextNode.getValue());
            move();
            return entry;
        }

        private void move() {
            if (nextNode != null && nextNode.getNext() != null) {
                nextNode = nextNode.getNext();
                return;
            }

            nextNode = null;
            while (curIndex < capacity) {
                nextNode = chains[curIndex];
                curIndex++;
                if (nextNode != null) {
                    return;
                }
            }
        }
    }
}
