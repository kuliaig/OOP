package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Реализация графа через список смежности.
 */
public class AdjacencyList implements Graph {

    private final Map<Integer, List<Integer>> list = new HashMap<>();

    @Override
    public void addVertex(int id) {
        list.putIfAbsent(id, new ArrayList<>());
    }

    @Override
    public void delVertex(int id) {
        list.remove(id);

        for (List<Integer> neighbors : list.values()) {
            neighbors.remove(Integer.valueOf(id));
        }
    }

    @Override
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);

        List<Integer> neighbors = list.get(from);
        neighbors.add(to);
    }

    @Override
    public void delEdge(int from, int to) {
        if (list.containsKey(from)) {
            list.get(from).remove(Integer.valueOf(to));
        }
    }

    @Override
    public List<Integer> getNeighbours(int id) {
        return list.getOrDefault(id, new ArrayList<>());
    }

    @Override
    public Set<Integer> getAllVertices() {
        return list.keySet();
    }

    @Override
    public void readFromFile(String path) {
        Input.readGraph(this, path);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int v : getAllVertices()) {
            str.append(v).append(": ").append(getNeighbours(v)).append("\n");
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Graph other)) {
            return false;
        }

        Set<Integer> thisVertices = getAllVertices();
        Set<Integer> otherVertices = other.getAllVertices();

        if (!thisVertices.equals(otherVertices)) {
            return false;
        }

        for (int v : thisVertices) {
            List<Integer> thisNeighbors = new ArrayList<>(getNeighbours(v));
            List<Integer> otherNeighbors = new ArrayList<>(other.getNeighbours(v));

            Collections.sort(thisNeighbors);
            Collections.sort(otherNeighbors);

            if (!thisNeighbors.equals(otherNeighbors)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return getAllVertices().hashCode();
    }
}
