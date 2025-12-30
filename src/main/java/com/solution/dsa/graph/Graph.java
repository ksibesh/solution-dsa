package com.solution.dsa.graph;

import java.util.*;

public class Graph<Vertex> {

    public record Edge<Vertex>(
            double weight,
            Vertex target
    ) {
    }

    private final Map<Vertex, Set<Edge<Vertex>>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        if (vertex == null) throw new IllegalArgumentException("vertex cannot be null");
        adjacencyList.putIfAbsent(vertex, new HashSet<>());
    }

    public void addEdge(Vertex source, double weight, Vertex target) {
        addVertex(source);
        addVertex(target);
        adjacencyList.get(source).add(new Edge<>(weight, target));
    }

    public void addEdge(Vertex source, Vertex target) {
        addEdge(source, 0.0, target);
    }

    public Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(adjacencyList.keySet());
    }

    public Set<Edge<Vertex>> getNeighbors(Vertex vertex) {
        return Collections.unmodifiableSet(
                adjacencyList.getOrDefault(vertex, Collections.emptySet())
        );
    }

    public boolean hasPath(Vertex source, Vertex target) {
        if (source == null || target == null) return false;
        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(target)) return false;
        if (source.equals(target)) return true;

        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> toVisit = new LinkedList<>();

        toVisit.add(source);
        while (!toVisit.isEmpty()) {
            Vertex visiting = toVisit.poll();
            for (Edge<Vertex> edge : getNeighbors(visiting)) {
                Vertex neighbor = edge.target();
                if (neighbor.equals(source)) return true;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    toVisit.add(neighbor);
                }
            }
        }
        return false;
    }
}
