package org.example;

import java.util.List;
import java.util.Set;

/**
 * Интерфейс Граф.
 */
public interface Graph {

    /**
     * Добавляет вершину в граф.
     *
     * @param id номер вершины.
     */
    void addVertex(int id);

    /**
     * Удаляет вершину из графа.
     *
     * @param id номер вершины.
     */
    void delVertex(int id);

    /**
     * Добавляет ребро в граф.
     *
     * @param from вершина, откуда идет ребро
     * @param to вершина, куда идет ребро
     */
    void addEdge(int from, int to);

    /**
     * Удаляет ребро из графа.
     *
     * @param from вершина, откуда идет ребро
     * @param to вершина, куда идет ребро
     */
    void delEdge(int from, int to);

    /**
     * Возвращает список вершин-соседей.
     *
     * @param id вершина
     * @return список вершин-соседей
     */
    List<Integer> getNeighbours(int id);

    /**
     * Возвращает список всех вершин графа.
     *
     * @return список вершин.
     */
    Set<Integer> getAllVertices();

    /**
     * Читает граф из файла.
     *
     * @param path путь к файлу
     */
    void readFromFile(String path);
}