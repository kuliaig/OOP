package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Тесты для всех реализаций графов.
 */
class GraphTest {

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testAddVertex(Graph graph) {
        graph.addVertex(5);
        assertTrue(graph.getAllVertices().contains(5));
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testDelVertex(Graph graph) {
        graph.addVertex(5);
        graph.addVertex(10);
        graph.delVertex(5);
        assertFalse(graph.getAllVertices().contains(5));
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testAddEdge(Graph graph) {
        graph.addEdge(5, 7);
        assertTrue(graph.getAllVertices().contains(5));
        assertTrue(graph.getAllVertices().contains(7));
        assertTrue(graph.getNeighbours(5).contains(7));
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testDelEdge(Graph graph) {
        graph.addEdge(5, 7);
        graph.addVertex(4);
        graph.addEdge(4, 8);
        graph.delEdge(5, 7);
        graph.delEdge(3, 4);
        assertTrue(graph.getAllVertices().contains(5));
        assertTrue(graph.getAllVertices().contains(7));
        assertTrue(graph.getAllVertices().contains(4));
        assertFalse(graph.getNeighbours(5).contains(7));
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    void testToString(Graph graph) {
        graph.addEdge(5, 7);

        String result = graph.toString();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.contains("5"));
        assertTrue(result.contains("7"));
    }

    @Test
    void testEqualsSameImplementation() {
        Graph a = new AdjacencyList();
        a.addEdge(0, 1);
        a.addEdge(1, 2);

        Graph b = new AdjacencyList();
        b.addEdge(0, 1);
        b.addEdge(1, 2);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testNotEqualsDifferentEdges() {
        Graph a = new AdjacencyList();
        a.addEdge(0, 1);

        Graph b = new AdjacencyList();
        b.addEdge(0, 2);

        assertNotEquals(a, b);
    }

    @Test
    void testEqualsAcrossImplementations() {
        Graph list = new AdjacencyList();
        list.addEdge(0, 1);
        list.addEdge(1, 2);

        Graph matrix = new AdjacencyMatrix();
        matrix.addEdge(0, 1);
        matrix.addEdge(1, 2);

        Graph incidence = new IncidenceMatrix();
        incidence.addEdge(0, 1);
        incidence.addEdge(1, 2);

        assertEquals(list, matrix);
        assertEquals(matrix, incidence);
        assertEquals(list, incidence);
    }

    @Test
    void testNotEqualsNullAndOtherClass() {
        Graph a = new AdjacencyList();
        a.addEdge(0, 1);

        assertNotEquals(a, null);
        assertNotEquals(a, "hehehe");
    }

    @Test
    void testHashCodeConsistency() {
        Graph a = new AdjacencyList();
        a.addEdge(0, 1);
        a.addEdge(1, 2);

        Graph b = new AdjacencyList();
        b.addEdge(0, 1);
        b.addEdge(1, 2);

        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testHashSetNoDuplicates() {
        Graph a = new AdjacencyList();
        a.addEdge(0, 1);

        Graph b = new AdjacencyMatrix();
        b.addEdge(0, 1);

        Set<Graph> set = new HashSet<>();
        set.add(a);
        set.add(b);

        assertEquals(1, set.size());
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
