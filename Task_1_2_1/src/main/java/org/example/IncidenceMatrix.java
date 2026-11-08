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
 * Реализация графа через матрицу инцидентности.
 */
public class IncidenceMatrix extends AbstractGraph {
    private int[][] matrix;
    private boolean[] exists;
    private int usedEdges;

    private int vertexSize;
    private int edgeSize;

    private final Map<Integer, Integer> idToIndex = new HashMap<>();
    private final List<Integer> indexToId = new ArrayList<>();
    private final List<int[]> edges = new ArrayList<>();

    public IncidenceMatrix() {
        vertexSize = 8;
        edgeSize = 8;
        usedEdges = 0;
        matrix = new int[vertexSize][edgeSize];
        exists = new boolean[vertexSize];
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
            if (index >= vertexSize) {
                doBiggerByVertix();
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

        for (int i = 0; i < usedEdges; i++) {
            if (matrix[index][i] != 0) {
                for (int j = 0; j < vertexSize; j++) {
                    if (j != index && matrix[j][i] != 0) {
                        matrix[j][i] = 0;
                        break;
                    }
                }
                matrix[index][i] = 0;
            }
        }
    }

    @Override
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);

        int indexFrom = idToIndex.get(from);
        int indexTo = idToIndex.get(to);

        if (usedEdges >= edgeSize) {
            doBiggerByEdges();
        }

        if (indexFrom == indexTo) {
            matrix[indexFrom][usedEdges] = 2;
        } else {
            matrix[indexFrom][usedEdges] = -1;
            matrix[indexTo][usedEdges] = 1;
        }

        edges.add(new int[]{from, to});
        usedEdges++;
    }

    @Override
    public void delEdge(int from, int to) {
        for (int e = 0; e < usedEdges; e++) {
            int[] edge = edges.get(e);
            if (edge == null) {
                continue;
            }

            if (edge[0] == from && edge[1] == to) {

                for (int i = 0; i < vertexSize; i++) {
                    matrix[i][e] = 0;
                }
                edges.set(e, null);
                return;
            }
        }
    }

    @Override
    public List<Integer> getNeighbours(int id) {
        List<Integer> result = new ArrayList<>();

        Integer i = idToIndex.get(id);
        if (i == null || !exists[i]) {
            return result;
        }

        for (int j = 0; j < usedEdges; j++) {
            if (matrix[i][j] == 2) {
                result.add(id);
            } else if (matrix[i][j] == -1) {
                int[] edge = edges.get(j);
                if (edge != null) {
                    result.add(edge[1]);
                }
            }
        }
        return result;
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

    private void doBiggerByVertix() {
        int newSize = vertexSize * 2;
        int[][] newMatrix = new int[newSize][edgeSize];

        for (int i = 0; i < vertexSize; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, edgeSize);
        }

        matrix = newMatrix;
        exists = Arrays.copyOf(exists, newSize);
        vertexSize = newSize;
    }

    private void doBiggerByEdges() {
        int newSize = edgeSize * 2;
        for (int i = 0; i < vertexSize; i++) {
            matrix[i] = Arrays.copyOf(matrix[i], newSize);
        }
        edgeSize = newSize;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int v : getAllVertices()) {
            str.append(v).append(": ").append(getNeighbours(v)).append("\n");
        }
        return str.toString();
    }
}
