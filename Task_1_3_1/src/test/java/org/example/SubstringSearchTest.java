package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SubstringSearchTest {

    private final SubstringSearch search = new SubstringSearch();

    private Path writeTemp(String content) throws IOException {
        Path file = Files.createTempFile("test", ".txt");
        file.toFile().deleteOnExit();
        Files.writeString(file, content, StandardCharsets.UTF_8);
        return file;
    }

    private List<Long> parseIndices(String s) {
        if (s == null || s.isBlank()) {
            return List.of();
        }
        return Arrays.stream(s.split(";"))
                .map(Long::parseLong)
                .toList();
    }

    @ParameterizedTest
    @CsvSource({
            "абракадабра, бра, 1;8",
            "ааа, аа, 0;1",
            "абв, x, ",
            "абра, аб, 0",
            "абра, ра, 2",
            "а, а, 0",
            "аааа, аа, 0;1;2",
    })
    void finds(String content, String substring, String expectedStr) throws IOException {
        Path file = writeTemp(content);
        List<Long> expected = parseIndices(expectedStr);
        assertEquals(expected, search.find(file.toString(), substring));
    }

    @Test
    void largeFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("big.txt");
        int repeats = 100_000;

        try (BufferedWriter w = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (int i = 0; i < repeats; i++) {
                w.write("ааа ");
            }
        }

        List<Long> result = search.find(file.toString(), "аа");
        assertEquals(repeats * 2, result.size());

        assertEquals(0L, result.get(0));
        assertEquals(1L, result.get(1));
        assertEquals(4L, result.get(2));
        assertEquals(5L, result.get(3));
    }

    @Test
    void different() throws IOException {
        Path file = writeTemp("Привет hello 123 мир hello");
        assertEquals(List.of(7L, 21L), search.find(file.toString(), "hello"));
    }

    @Test
    void longSubstring() throws IOException {
        String substring = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        Path file = writeTemp("aaaaaa" + substring + "bbbbbb" + substring);
        assertEquals(List.of(6L, 45L), search.find(file.toString(), substring));
    }

    @Test
    void mixed(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("mixed.txt");
        try (BufferedWriter w = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            w.write("абрекадабре ");
            w.write("ааааа ");
            w.write("брбрбре ");
            w.write("xбреy бреz");
            w.write(" ");
            w.write("бре");
        }

        List<Long> result = search.find(file.toString(), "бре");
        assertEquals(6, result.size());
    }
}
