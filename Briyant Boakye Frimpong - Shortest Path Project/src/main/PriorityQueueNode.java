/**
 * This class represents a a node in a priority queue. It stores a reference to a GraphNode and a
 * pointer to the next node in the priority queue.
 * Known Bugs: None
 *
 * @author Briyant Boakye Frimpong
 * briyantbf@brandeis.edu
 * December, 2024
 * COSI 21A PA3
 */

package main;

public class PriorityQueueNode {
    private GraphNode node;
    private PriorityQueueNode next;

    /**
     * This method constructs a new node with the next pointer set as null.
     * 
     * @param node is the GraphNode
     */
    public PriorityQueueNode(GraphNode node) {
        this.node = node;
        this.next = null;
    }

    /**
     * This method returns the GraphNode.
     * 
     * @return the GraphNode
     */
    public GraphNode getNode() {
        return this.node;
    }

    /**
     * This method returns the next node in the priority queue.
     *
     * @return the next node
     */
    public PriorityQueueNode getNext() {
        return this.next;
    }

    /**
     * This method sets the next node in the priority queue.
     *
     * @param next is the next node being set
     */
    public void setNext(PriorityQueueNode next) {
        this.next = next;
    }
}