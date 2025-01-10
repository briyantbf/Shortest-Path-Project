/**
 * This class tests the functionality of the min-priority queue class.
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

import main.MinPriorityQueue;
import main.GraphNode;

public class MinPriorityQueueTest {
    @Test
    public void insertTest() {
        // Initialize a priority queue with a capacity of 10
        MinPriorityQueue priorityQueue = new MinPriorityQueue(10);
        
        // Initialize graph nodes with priorities
        GraphNode node1 = new GraphNode("node1", false);
        node1.priority = 23;
        GraphNode node2 = new GraphNode("node2", false);
        node2.priority = 12;
        GraphNode node3 = new GraphNode("node3", false);
        node3.priority = 46;

        // Insert nodes into the priority queue
        priorityQueue.insert(node1);
        priorityQueue.insert(node2);
        priorityQueue.insert(node3);

        // Asserts that the queue is not empty
        assertFalse(priorityQueue.isEmpty());

        // Asserts that the node with the highest priority is at the head
        assertEquals(node2, priorityQueue.pullHighestPriorityElement());
        assertEquals(node1, priorityQueue.pullHighestPriorityElement());
        assertEquals(node3, priorityQueue.pullHighestPriorityElement());
    }

    @Test
    public void removeTest() {
        // Initialize a priority queue with a capacity of 10
        MinPriorityQueue priorityQueue = new MinPriorityQueue(10);

        // Initialize graph nodes with priorities
        GraphNode node1 = new GraphNode("node1", false);
        node1.priority = 23;
        GraphNode node2 = new GraphNode("node2", false);
        node2.priority = 12;
        GraphNode node3 = new GraphNode("node3", false);
        node3.priority = 46;

        // Insert nodes into the priority queue
        priorityQueue.insert(node1);
        priorityQueue.insert(node2);
        priorityQueue.insert(node3);

        // Removes node2
        priorityQueue.remove(node2);

        // Asserts that node2 isn't in the queue
        assertFalse(priorityQueue.contains(node2));

        // Asserts that the order of the elements left are correct
        assertEquals(node1, priorityQueue.pullHighestPriorityElement());
        assertEquals(node3, priorityQueue.pullHighestPriorityElement());

        // Asserst that the queue is empty
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void pullHighestPriorityElementTest() {
        // Initialize a priority queue with a capacity of 10
        MinPriorityQueue priorityQueue = new MinPriorityQueue(10);

        // Initialize graph nodes with priorities
        GraphNode node1 = new GraphNode("node1", false);
        node1.priority = 23;
        GraphNode node2 = new GraphNode("node2", false);
        node2.priority = 12;
        GraphNode node3 = new GraphNode("node3", false);
        node3.priority = 46;

        // Insert nodes into the priority queue
        priorityQueue.insert(node1);
        priorityQueue.insert(node2);
        priorityQueue.insert(node3);

        // Asserts that the node with the highest priority is at the head
        assertEquals(node2, priorityQueue.pullHighestPriorityElement());
        assertEquals(node1, priorityQueue.pullHighestPriorityElement());
        assertEquals(node3, priorityQueue.pullHighestPriorityElement());

        // Asserts that the queue is empty
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void rebalanceTest() {
        // Initialize a priority queue with a capacity of 10
        MinPriorityQueue priorityQueue = new MinPriorityQueue(10);

        // Initialize graph nodes with priorities
        GraphNode node1 = new GraphNode("node1", false);
        node1.priority = 23;
        GraphNode node2 = new GraphNode("node2", false);
        node2.priority = 12;

        // Insert nodes into the priority queue
        priorityQueue.insert(node1);
        priorityQueue.insert(node2);

        // Rebalance node1 after changing its priority
        node1.priority = 1;
        priorityQueue.rebalance(node1);

        // Asserts that the node with the highest priority is at the head
        assertEquals(node1, priorityQueue.pullHighestPriorityElement());
        assertEquals(node2, priorityQueue.pullHighestPriorityElement());

        // Asserts that the queue is empty
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void isEmptyTest() {
        // Initialize a priority queue with a capacity of 10
        MinPriorityQueue priorityQueue = new MinPriorityQueue(10);

        // Asserts the queue is empty before anything
        assertTrue(priorityQueue.isEmpty());

        // Initialize a graph node with priorities
        GraphNode node1 = new GraphNode("node1", false);
        node1.priority = 23;

        // Insert nodes into the priority queue and asserts the queue isn't empty after that
        priorityQueue.insert(node1);
        assertFalse(priorityQueue.isEmpty());

        // Pull the node and asserts the queue is empty again
        priorityQueue.pullHighestPriorityElement();
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void containsTest() {
        // Initialize a priority queue with a capacity of 10
        MinPriorityQueue priorityQueue = new MinPriorityQueue(10);
        
        // Initialize graph nodes with priorities
        GraphNode node1 = new GraphNode("node1", false);
        node1.priority = 23;
        GraphNode node2 = new GraphNode("node2", false);
        node2.priority = 12;

        // Insert nodes into the priority queue
        priorityQueue.insert(node1);
        priorityQueue.insert(node2);


        // Asserts the queue contains all nodes
        assertTrue(priorityQueue.contains(node1));
        assertTrue(priorityQueue.contains(node2));

        // Removes node2 and asserts that node isn't in the queue
        priorityQueue.remove(node2);
        assertFalse(priorityQueue.contains(node2));
    }

    @Test
    public void resizeTest() {
        // Initialize a priority queue with a capacity of 2 to force resizing
        MinPriorityQueue priorityQueue = new MinPriorityQueue(2);
        
        // Initialize graph nodes with priorities
        GraphNode node1 = new GraphNode("node1", false);
        node1.priority = 23;
        GraphNode node2 = new GraphNode("node2", false);
        node2.priority = 12;
        GraphNode node3 = new GraphNode("node3", false);
        node3.priority = 46;

        // Insert nodes into the priority queue
        priorityQueue.insert(node1);
        priorityQueue.insert(node2);

        // Asserts that the capacity is correct and contains all nodes
        assertEquals(2, priorityQueue.getCapacity());
        assertTrue(priorityQueue.contains(node1));
        assertTrue(priorityQueue.contains(node2));

        // Inserts third node to force resizing
        priorityQueue.insert(node3);

        // Asserts that the capacity was doubled and contains all nodes
        assertEquals(4, priorityQueue.getCapacity());
        assertTrue(priorityQueue.contains(node1));
        assertTrue(priorityQueue.contains(node2));
        assertTrue(priorityQueue.contains(node3));
    }
}