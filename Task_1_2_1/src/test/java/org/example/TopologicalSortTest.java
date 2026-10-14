package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Тесты для топологической сортировки.
 */
class TopologicalSortTest {

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testSimpleSort(Graph graph) {
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        List<Integer> sorted = TopologicalSort.sort(graph);

        assertEquals(4, sorted.size());
        assertTrue(sorted.indexOf(0) < sorted.indexOf(1));
        assertTrue(sorted.indexOf(0) < sorted.indexOf(2));
        assertTrue(sorted.indexOf(1) < sorted.indexOf(3));
        assertTrue(sorted.indexOf(2) < sorted.indexOf(3));
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testEmptyGraph(Graph graph) {
        List<Integer> sorted = TopologicalSort.sort(graph);

        assertTrue(sorted.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testSingleVertex(Graph graph) {
        graph.addVertex(5);

        List<Integer> sorted = TopologicalSort.sort(graph);

        assertEquals(1, sorted.size());
        assertEquals(5, sorted.get(0));
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testCycle(Graph graph) {
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        assertThrows(IllegalArgumentException.class,
                () -> TopologicalSort.sort(graph));
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testLoop(Graph graph) {
        graph.addEdge(5, 5);

        assertThrows(IllegalArgumentException.class,
                () -> TopologicalSort.sort(graph));
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testIsolated(Graph graph) {
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);

        List<Integer> sorted = TopologicalSort.sort(graph);

        assertEquals(3, sorted.size());
        assertTrue(sorted.contains(0));
        assertTrue(sorted.contains(1));
        assertTrue(sorted.contains(2));
    }

    /**
     * Создает все реализации графа для тестов.
     *
     * @return поток реализаций
     */
    static Stream<Graph> createGraphs() {
        return Stream.of(
                new AdjacencyList(),
                new AdjacencyMatrix(),
                new IncidenceMatrix()
        );
    }
}