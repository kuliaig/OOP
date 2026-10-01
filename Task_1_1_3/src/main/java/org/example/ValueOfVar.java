package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Означивание переменных
 */
public class ValueOfVar {

    private final Map<String, Integer> values = new HashMap<>();

    /**
     * Создает список переменных и их значений.
     *
     * @param input "x = 10; y = 13"
     */
    public ValueOfVar(String input) {
        String[] sentences = input.split(";");

        for (String sentence : sentences) {
            sentence = sentence.trim();
            if (sentence.isEmpty()) {
                continue;
            }

            String[] nameAndValue = sentence.split("=");
            if (nameAndValue.length != 2) {
                throw new IllegalArgumentException("Неверный формат: " + sentence);
            }

            String name = nameAndValue[0].trim();
            int value = Integer.parseInt(nameAndValue[1].trim());

            if (values.containsKey(name)) {
                throw new IllegalArgumentException("Переменная " + name + " задана дважды");
            }
            values.put(name, value);
        }
    }

    /**
     * Возвращает значение конкретной переменной.
     *
     * @param name имя переменной
     * @return значение
     */
    public int get(String name) {
        if (!values.containsKey(name)) {
            throw new IllegalArgumentException("Переменная " + name + " не задана");
        }
        return values.get(name);
    }
}
