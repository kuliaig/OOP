package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Тесты для чтения графа из файла.
 */
class InputTest {

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testReadFromFile(Graph graph) throws IOException {
        Path file = Files.createTempFile("graph", ".txt");
        Files.writeString(file, "5 4\n0 1\n1 2\n2 3\n3 4\n");

        graph.readFromFile(file.toString());

        assertEquals(5, graph.getAllVertices().size());
        assertTrue(graph.getNeighbours(0).contains(1));
        assertTrue(graph.getNeighbours(1).contains(2));
        assertTrue(graph.getNeighbours(2).contains(3));
        assertTrue(graph.getNeighbours(3).contains(4));

        Files.deleteIfExists(file);
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testReadFromFileNotFound(Graph graph) {
        assertThrows(IllegalArgumentException.class,
                () -> graph.readFromFile("no-such-file.txt"));
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testReadFromFileWrongFormat(Graph graph) throws IOException {
        Path file = Files.createTempFile("bad", ".txt");
        Files.writeString(file, "abc def\n");

        assertThrows(Exception.class, () -> graph.readFromFile(file.toString()));

        Files.deleteIfExists(file);
    }

    static Stream<Graph> createGraphs() {
        return Stream.of(
                new AdjacencyList(),
                new AdjacencyMatrix(),
                new IncidenceMatrix()
        );
    }
}