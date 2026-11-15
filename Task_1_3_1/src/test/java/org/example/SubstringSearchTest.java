package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SubstringSearchTest {

    private final SubstringSearch search = new SubstringSearch();

    private Path writeTemp(String content) throws IOException {
        Path file = Files.createTempFile("test", ".txt");
        file.toFile().deleteOnExit();
        Files.writeString(file, content, StandardCharsets.UTF_8);
        return file;
    }

    @Test
    void example() throws IOException {
        Path file = writeTemp("абракадабра");
        assertEquals(List.of(1L, 8L), search.find(file.toString(), "бра"));
    }

    @Test
    void empty() throws IOException {
        Path file = writeTemp("абракадабра");
        assertEquals(List.of(), search.find(file.toString(), ""));
    }

    @Test
    void fileNotFound() {
        assertThrows(IOException.class, () -> search.find("no_such_file.txt", "бра"));
    }

    @Test
    void overlapping() throws IOException {
        Path file = writeTemp("ааа");
        assertEquals(List.of(0L, 1L), search.find(file.toString(), "аа"));
    }

    @Test
    void emptyFile() throws IOException {
        Path file = writeTemp("");
        assertEquals(List.of(), search.find(file.toString(), "а"));
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
}
