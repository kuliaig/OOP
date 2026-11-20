package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для поиска подстроки в файле.
 */
public class SubstringSearch {

    /**
     * Поиск подстроки в файле.
     * @param file файл
     * @param substring подстрока
     * @return массив индексов начала вхождения
     * @throws IOException если файл не удалось прочитать
     */
    public List<Long> find(String file, String substring) throws IOException {
        List<Long> result = new ArrayList<>();

        if (substring.isEmpty()) {
            return result;
        }

        char[] charSubstring = substring.toCharArray();
        int[] pi = createPi(charSubstring);

        int matched = 0;
        long position = 0;

        try (BufferedReader reader = Files.newBufferedReader(
                Path.of(file), StandardCharsets.UTF_8)) {
            int symb;

            while ((symb = reader.read()) != -1) {
                matched = step((char) symb, charSubstring, pi, matched);

                if (matched == charSubstring.length) {
                    result.add(position - charSubstring.length + 1);
                    matched = pi[matched - 1];
                }

                position++;
            }
        }

        return result;
    }

    private int step(char c, char[] substring, int[] pi, int matched) {
        while (matched > 0 && c != substring[matched]) {
            matched = pi[matched - 1];
        }
        if (c == substring[matched]) {
            matched++;
        }
        return matched;
    }

    private int[] createPi(char[] substring) {
        int[] pi = new int[substring.length];
        if (substring.length == 0) {
            return pi;
        }
        pi[0] = 0;
        int j = 0;

        for (int i = 1; i < substring.length; i++) {
            while (j > 0 && substring[i] != substring[j]) {
                j = pi[j - 1];
            }

            if (substring[i] == substring[j]) {
                j++;
            }

            pi[i] = j;
        }
        return pi;
    }
}