package graph;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {
    private Graph<String> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testInitialVerticesEmpty() {
        assertTrue("Graph should be empty initially", graph.getVertices().isEmpty());
    }

    @Test
    public void testAddVertex() {
        graph.addVertex("A");
        assertTrue("Graph should contain vertex A", graph.getVertices().contains("A"));
    }

    @Test
    public void testAddDuplicateVertex() {
        graph.addVertex("A");
        graph.addVertex("A"); // Attempt to add duplicate
        assertEquals("Graph should still contain only one A", 1, graph.getVertices().stream().filter(v -> v.equals("A")).count());
    }

    @Test
    public void testSetEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.setEdge("A", "B", 5);
        assertTrue("Edge from A to B should exist with weight 5", graph.hasEdge("A", "B"));
        assertEquals("Weight of edge from A to B should be 5", 5, graph.getEdgeWeight("A", "B"));
    }

    @Test
    public void testRemoveVertex() {
        graph.addVertex("A");
        graph.removeVertex("A");
        assertFalse("Graph should not contain vertex A after removal", graph.getVertices().contains("A"));
    }

    @Test
    public void checkSourcesAndTargets() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.setEdge("A", "B", 3);
        
        assertTrue("Sources of B should include A", graph.getSources("B").contains("A"));
        assertTrue("Targets of A should include B", graph.getTargets("A").contains("B"));
    }
}
