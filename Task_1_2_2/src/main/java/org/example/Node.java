package org.example;

/**
 * Один узел.
 */
class Node<Key, Value> {
    Key key;
    Value value;
    Node<Key, Value> next;

    Node(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Возвращает ключ.
     *
     * @return ключ
     */
    Key getKey() {
        return key;
    }

    /**
     * Возвращает значение.
     *
     * @return value
     */
    Value getValue() {
        return value;
    }

    /**
     * Изменяет значение узла.
     *
     * @param value значение
     */
    void changeValue(Value value) {
        this.value = value;
    }

    /**
     * Возвращает следующее значение из списка
     * с таким же значением хэш-функции.
     *
     * @return следующий узел
     */
    Node<Key, Value> getNext() {
        return next;
    }

    /**
     * Устанавливает следующий узел для текущего.
     *
     * @param next следующий
     */
    void setNext(Node<Key, Value> next) {
        this.next = next;
    }
}
