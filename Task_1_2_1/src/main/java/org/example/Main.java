package org.example;

/**
 * Точка входа.
 */
public class Main {

    /**
     * Мини топологическая сортировка.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Graph graph = new AdjacencyList();

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        System.out.println(TopologicalSort.sort(graph));
    }
}