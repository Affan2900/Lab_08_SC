package graph;

import java.util.*;

public class ConcreteVerticesGraph implements Graph<Vertex> {
    private final List<Vertex> vertices = new ArrayList<>();
    private final Map<Vertex, List<Edge<Vertex>>> adjacencyList = new HashMap<>();

    @Override
    public void addVertex(Vertex vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            adjacencyList.put(vertex, new ArrayList<>());
        }
    }

    @Override
    public void removeVertex(Vertex vertex) {
        vertices.remove(vertex);
        adjacencyList.remove(vertex);
        for (List<Edge<Vertex>> edges : adjacencyList.values()) {
            edges.removeIf(edge -> edge.getTarget().equals(vertex));
        }
    }

    @Override
    public boolean hasEdge(Vertex source, Vertex target) {
        return adjacencyList.containsKey(source) && 
               adjacencyList.get(source).stream().anyMatch(edge -> edge.getTarget().equals(target));
    }

    @Override
    public void setEdge(Vertex source, Vertex target, int weight) {
        addVertex(source);
        addVertex(target);
        removeEdge(source, target); // Remove any existing edge before adding a new one
        adjacencyList.get(source).add(new Edge<>(target, weight));
    }

    @Override
    public int getEdgeWeight(Vertex source, Vertex target) {
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
    public Set<Vertex> getVertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public Set<Vertex> getSources(Vertex target) {
        Set<Vertex> sources = new HashSet<>();
        for (Map.Entry<Vertex, List<Edge<Vertex>>> entry : adjacencyList.entrySet()) {
            for (Edge<Vertex> edge : entry.getValue()) {
                if (edge.getTarget().equals(target)) {
                    sources.add(entry.getKey());
                }
            }
        }
        return sources;
    }

    @Override
    public Set<Vertex> getTargets(Vertex source) {
        Set<Vertex> targets = new HashSet<>();
        if (adjacencyList.containsKey(source)) {
            for (Edge<Vertex> edge : adjacencyList.get(source)) {
                targets.add(edge.getTarget());
            }
        }
        return targets;
    }

    private void removeEdge(Vertex source, Vertex target) {
        List<Edge<Vertex>> edges = adjacencyList.get(source);
        if (edges != null) {
            edges.removeIf(edge -> edge.getTarget().equals(target));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ConcreteVerticesGraph:\n");
        for (Vertex vertex : vertices) {
            sb.append(vertex.toString()).append(" -> ");
            List<Edge<Vertex>> edges = adjacencyList.get(vertex);
            if (edges != null && !edges.isEmpty()) {
                for (Edge<Vertex> edge : edges) {
                    sb.append(edge.getTarget().getId()).append("(").append(edge.getWeight()).append("), ");
                }
                sb.setLength(sb.length() - 2); // Remove last comma and space
            } else {
                sb.append("No outgoing edges");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}