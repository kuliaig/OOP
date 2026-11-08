package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {

    public static void readGraph(Graph graph, String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            int vertexCount = scanner.nextInt();
            int edgeCount = scanner.nextInt();

            for (int i = 0; i < vertexCount; i++) {
                graph.addVertex(i);
            }

            for (int i = 0; i < edgeCount; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                graph.addEdge(from, to);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Файл " + path + " не найден.");
        }
    }
}
