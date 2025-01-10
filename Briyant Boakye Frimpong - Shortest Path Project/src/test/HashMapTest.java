/**
 * This class tests the functionality of the heapmap class.
 * Known Bugs: None
 *
 * @author Briyant Boakye Frimpong
 * briyantbf@brandeis.edu
 * December, 2024
 * COSI 21A PA3
 */

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.GraphNode;
import main.HashMap;

public class HashMapTest {
    @Test
    public void setAndGetValueTest() {
        // Initializes a hashmap with graph nodes
        HashMap hashMap = new HashMap(10);
        GraphNode node1 = new GraphNode("node1", false);
        GraphNode node2 = new GraphNode("node2", false);

        // Sets the graph nodes
        hashMap.set(node1, 10);
        hashMap.set(node2, 20);

        // Asserts that the graph nodes are correctly
        assertEquals(10, hashMap.getValue(node1));
        assertEquals(20, hashMap.getValue(node2));
    }

    @Test
    public void updateValueTest() {
        // Initializes a hashmap with a graph node
        HashMap hashMap = new HashMap(10);
        GraphNode node1 = new GraphNode("node1", false);

        // Sets and asserts that the graph node value was set
        hashMap.set(node1, 10);
        assertEquals(10, hashMap.getValue(node1));

        // Updates the value and asserts the value of the node was updated
        hashMap.set(node1, 15);
        assertEquals(15, hashMap.getValue(node1));
    }

    @Test
    public void hasKeyTest() {
        // Initializes a hashmap with graph nodes
        HashMap hashMap = new HashMap(10);
        GraphNode node1 = new GraphNode("node1", false);
        GraphNode node2 = new GraphNode("node2", false);

        // Sets the graph node for node1
        hashMap.set(node1, 10);

        // Asserts that node1 has a key
        assertTrue(hashMap.hasKey(node1));

        // Asserts that node2 doesn't have a key
        assertFalse(hashMap.hasKey(node2));
    }

    @Test
    public void sizeTest() {
        // Initializes a hashmap with graph nodes
        HashMap hashMap = new HashMap(10);
        HashMap hashMap2 = new HashMap();
        GraphNode node1 = new GraphNode("node1", false);
        GraphNode node2 = new GraphNode("node2", false);

        // Asserts that the size is accurate with the nodes in the hashmap
        assertEquals(0, hashMap.size());
        assertEquals(0, hashMap2.size());

        hashMap.set(node1, 10);
        assertEquals(1, hashMap.size());

        hashMap.set(node2, 20);
        assertEquals(2, hashMap.size());

        // Ensures size doesn't change even after updating a key
        hashMap.set(node1, 15);
        assertEquals(2, hashMap.size());
    }

    @Test
    public void collisionHandlingTest() {
        // Initializes a hashmap with graph nodes with the same id to simulate collision
        HashMap hashMap = new HashMap(10);
        GraphNode node1 = new GraphNode("collision", false);
        GraphNode node2 = new GraphNode("collision", false);

        // Sets the two nodes which should lead to collision
        hashMap.set(node1, 21);
        hashMap.set(node2, 69);

        // Asserts that the nodes were still set correctly
        assertEquals(21, hashMap.getValue(node1));
        assertEquals(69, hashMap.getValue(node2));
    }

    @Test
    public void rehashingTest() {
        // Initializes a hashmap with a small capacity and graph nodes to force rehashing
        HashMap hashMap = new HashMap(3);
        GraphNode node1 = new GraphNode("rehash", false);
        GraphNode node2 = new GraphNode("rehash", false);
        GraphNode node3 = new GraphNode("rehash", false);

        // Sets the graph nodes and asserts the correct sizing
        hashMap.set(node1, 1);
        assertEquals(1, hashMap.size());
        hashMap.set(node2, 2);
        assertEquals(2, hashMap.size());
        assertEquals(3, hashMap.capacity());
        
        // Sets the graph node that causes rehashing
        hashMap.set(node3, 3);
        assertEquals(3, hashMap.size());

        // Asserts the correct capacity
        assertEquals(6, hashMap.capacity());

        // Asserts that all nodes were set and rehashing happened
        assertTrue(hashMap.hasKey(node1));
        assertTrue(hashMap.hasKey(node2));
        assertTrue(hashMap.hasKey(node3));
    }

    @Test
    public void removeTest() {
        // Initializes a hashmap and adds a node
        HashMap hashMap = new HashMap(10);
        GraphNode node1 = new GraphNode("node1", false);
        GraphNode node2 = new GraphNode("node2", false);

        // Sets the graph nodes
        hashMap.set(node1, 10);
        hashMap.set(node2, 20);

        // Removes node1 and asserts it was removed correctly
        assertTrue(hashMap.remove(node1));
        assertFalse(hashMap.hasKey(node1));
        assertEquals(1, hashMap.size());

        // Should return false since node1 is already removed
        assertFalse(hashMap.remove(node1));
    }
} 