package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Базовый абстрактный граф с общими методами.
 */
public abstract class AbstractGraph implements Graph {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Graph)) {
            return false;
        }

        Graph other = (Graph) obj;

        Set<Integer> thisVertices = getAllVertices();
        Set<Integer> otherVertices = other.getAllVertices();

        if (!thisVertices.equals(otherVertices)) {
            return false;
        }

        for (int v : thisVertices) {
            List<Integer> thisNeighbours = new ArrayList<>(getNeighbours(v));
            List<Integer> otherNeighbours = new ArrayList<>(other.getNeighbours(v));

            Collections.sort(thisNeighbours);
            Collections.sort(otherNeighbours);

            if (!thisNeighbours.equals(otherNeighbours)) {
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