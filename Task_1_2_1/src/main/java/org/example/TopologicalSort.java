package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Топологическая сортировка.
 */
public class TopologicalSort {

    /**
     * Производит топологическую сортировку графа.
     *
     * @param graph граф
     * @return список отсортированных вершин
     */
    public static List<Integer> sort(Graph graph) {
        Set<Integer> vertix = graph.getAllVertices();

        Map<Integer, Integer> degrees = new HashMap<>();

        for (Integer v : vertix) {
            degrees.put(v, 0);
        }

        for (Integer from : vertix) {
            for (Integer to : graph.getNeighbours(from)) {
                degrees.merge(to, 1, Integer::sum);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (Integer v : vertix) {
            if (degrees.get(v) == 0) {
                queue.add(v);
            }
        }

        List <Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int nowVer = queue.remove();
            result.add(nowVer);

            for (Integer v : graph.getNeighbours(nowVer)) {
                degrees.merge(v, -1, Integer::sum);
                if (degrees.get(v) == 0) {
                    queue.add(v);
                }
            }
        }

        if (result.size() != vertix.size()) {
            throw new IllegalArgumentException("Граф содержит цикл.");
        }

        return result;
    }
}