package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Реализация графа через матрицу смежности.
 */
public class AdjacencyMatrix implements Graph {

    private boolean[][] matrix;
    private boolean[] exists;
    private int size;

    private final Map<Integer, Integer> idToIndex = new HashMap<>();
    private final List<Integer> indexToId = new ArrayList<>();

    public AdjacencyMatrix() {
        size = 16;
        matrix = new boolean[size][size];
        exists = new boolean[size];
    }

    @Override
    public void addVertex(int id) {
        if (idToIndex.containsKey(id)) {
            return;
        }

        int index = -1;
        for (int i = 0; i < indexToId.size(); i++) {
            if (!exists[i]) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            index = indexToId.size();
            if (index >= size) {
                doBigger();
            }
            indexToId.add(id);
        } else {
            indexToId.set(index, id);
        }

        idToIndex.put(id, index);
        exists[index] = true;
    }

    @Override
    public void delVertex(int id) {
        Integer index = idToIndex.remove(id);
        if (index == null) {
            return;
        }

        indexToId.set(index, null);
        exists[index] = false;

        for (int i = 0; i < indexToId.size(); i++) {
            matrix[index][i] = false;
            matrix[i][index] = false;
        }
    }

    @Override
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);

        int indexFrom = idToIndex.get(from);
        int indexTo = idToIndex.get(to);

        matrix[indexFrom][indexTo] = true;
    }

    @Override
    public void delEdge(int from, int to) {
        Integer indexFrom = idToIndex.get(from);
        Integer indexTo = idToIndex.get(to);

        if (indexFrom == null || indexTo == null) {
            return;
        }

        matrix[indexFrom][indexTo] = false;
    }

    @Override
    public List<Integer> getNeighbours(int id) {
        List<Integer> result = new ArrayList<>();

        Integer i = idToIndex.get(id);
        if (i == null || !exists[i]) {
            return result;
        }

        for (int j = 0; j < size; j++) {
            if (matrix[i][j]) {
                Integer neighborId = indexToId.get(j);
                if (neighborId != null) {
                    result.add(neighborId);
                }
            }
        }
        return result;
    }

    private void doBigger() {
        int newSize = size * 2;
        boolean[][] newMatrix = new boolean[newSize][newSize];

        for (int i = 0; i < indexToId.size(); i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, size);
        }

        matrix = newMatrix;
        exists = Arrays.copyOf(exists, newSize);

        size = newSize;
    }

    @Override
    public Set<Integer> getAllVertices() {
        Set<Integer> vertices = new HashSet<>();

        for (int i = 0; i < indexToId.size(); i++) {
            if (exists[i] && indexToId.get(i) != null) {
                vertices.add(indexToId.get(i));
            }
        }

        return vertices;
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
