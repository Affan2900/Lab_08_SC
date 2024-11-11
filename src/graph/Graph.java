package graph;

import java.util.Set;

/**
 * A generic interface for a directed graph.
 *
 * @param <V> the type of vertices in the graph
 */
public interface Graph<V> {

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add
     */
    void addVertex(V vertex);

    /**
     * Removes a vertex from the graph.
     *
     * @param vertex the vertex to remove
     */
    void removeVertex(V vertex);

    /**
     * Checks if there is an edge from source to target.
     *
     * @param source the source vertex
     * @param target the target vertex
     * @return true if there is an edge, false otherwise
     */
    boolean hasEdge(V source, V target);

    /**
     * Sets an edge from source to target with a specified weight.
     *
     * @param source the source vertex
     * @param target the target vertex
     * @param weight the weight of the edge
     */
    void setEdge(V source, V target, int weight);

    /**
     * Gets the weight of the edge from source to target.
     *
     * @param source the source vertex
     * @param target the target vertex
     * @return the weight of the edge
     */
    int getEdgeWeight(V source, V target);

    /**
     * Gets all vertices in the graph.
     *
     * @return a set of vertices
     */
    Set<V> getVertices();

    /**
     * Gets all sources for a given target vertex.
     *
     * @param target the target vertex
     * @return a set of source vertices
     */
    Set<V> getSources(V target);

    /**
     * Gets all targets for a given source vertex.
     *
     * @param source the source vertex
     * @return a set of target vertices
     */
    Set<V> getTargets(V source);

    /**
     * A static nested class representing an edge in the graph.
     *
     * @param <V> the type of vertices in this edge
     */
    static class Edge<V> {
        private final V target;
        private final int weight;

        public Edge(V target, int weight) {
            this.target = target;
            this.weight = weight;
        }

        public V getTarget() {
            return target;
        }

        public int getWeight() {
            return weight;
        }
    }
}