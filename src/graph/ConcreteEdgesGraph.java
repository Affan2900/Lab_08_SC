package graph;

import java.util.*;

public class ConcreteEdgesGraph<V> implements Graph<V> {
    // Map to hold vertices and their corresponding edges
    private final Map<V, List<Edge<V>>> adjacencyList;

    public ConcreteEdgesGraph() {
        this.adjacencyList = new HashMap<>();
    }

    @Override
    public void addVertex(V vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void removeVertex(V vertex) {
        adjacencyList.remove(vertex);
        for (List<Edge<V>> edges : adjacencyList.values()) {
            edges.removeIf(edge -> edge.getTarget().equals(vertex));
        }
    }

    @Override
    public boolean hasEdge(V source, V target) {
        return adjacencyList.containsKey(source) && 
               adjacencyList.get(source).stream().anyMatch(edge -> edge.getTarget().equals(target));
    }

    @Override
    public void setEdge(V source, V target, int weight) {
        addVertex(source);
        addVertex(target);
        removeEdge(source, target); // Remove any existing edge before adding a new one
        adjacencyList.get(source).add(new Edge<>(target, weight));
    }

    @Override
    public int getEdgeWeight(V source, V target) {
        if (hasEdge(source, target)) {
            return adjacencyList.get(source).stream()
                    .filter(edge -> edge.getTarget().equals(target))
                    .findFirst()
                    .get()
                    .getWeight();
        }
        throw new NoSuchElementException("No edge from " + source + " to " + target);
    }

    @Override
    public Set<V> getVertices() {
        return new HashSet<>(adjacencyList.keySet());
    }

    @Override
    public Set<V> getSources(V target) {
        Set<V> sources = new HashSet<>();
        for (Map.Entry<V, List<Edge<V>>> entry : adjacencyList.entrySet()) {
            for (Edge<V> edge : entry.getValue()) {
                if (edge.getTarget().equals(target)) {
                    sources.add(entry.getKey());
                }
            }
        }
        return sources;
    }

    @Override
    public Set<V> getTargets(V source) {
        Set<V> targets = new HashSet<>();
        if (adjacencyList.containsKey(source)) {
            for (Edge<V> edge : adjacencyList.get(source)) {
                targets.add(edge.getTarget());
            }
        }
        return targets;
    }

    private void removeEdge(V source, V target) {
        List<Edge<V>> edges = adjacencyList.get(source);
        if (edges != null) {
            edges.removeIf(edge -> edge.getTarget().equals(target));
        }
    }
}