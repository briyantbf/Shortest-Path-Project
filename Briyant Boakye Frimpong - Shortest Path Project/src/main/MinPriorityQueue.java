/**
 * This class implements a min-priority queue that allows you to add items with certain priorities,
 * and then be able to extract the item with the lowest priority in O(1) time.
 * Known Bugs: None
 *
 * @author Briyant Boakye Frimpong
 * briyantbf@brandeis.edu
 * December, 2024
 * COSI 21A PA3
 */

package main;

public class MinPriorityQueue {
    private PriorityQueueNode head;
    private HashMap map;
    private int size;
    private int capacity;

    /**
     * This is the constructor method which initializes components of the priority queue such as
     * the user-defined capacity, head pointer, hashmap, and size.
     * 
     * @param capacity is the size of the hashmap
     */
    public MinPriorityQueue(int capacity) {
        this.head = null;
        this.size = 0;
        this.capacity = capacity;
        this.map = new HashMap(this.capacity);
    }

    /**
     * This method inserts the graph node g into the queue with its priority.
     * 
     * @param g is the graph node being inserted into the queue
     */
    public void insert(GraphNode g) {
        // Resizes the queue if needed
        if (this.size == this.capacity) {
            resize();
        }
        
        PriorityQueueNode newNode = new PriorityQueueNode(g);

        // Adds the new node to the front of the priority queue
        if (this.head == null || this.head.getNode().priority > g.priority) {
            newNode.setNext(this.head);
            this.head = newNode;
            this.map.set(g, 0);
        }
        else {
            // Iterates through the priority queue to find the index to place the new node
            PriorityQueueNode curr = this.head;
            int index = 0;

            while (curr.getNext() != null && curr.getNode().priority <= g.priority) {
                curr = curr.getNext();
                index++;
            }
            // Updates the pointers
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
            // Updates index
            this.map.set(g, index + 1);
        }
        size++;
    }

    /**
     * This method removes a GraphNode from the priority queue.
     * 
     * @param g is the GraphNode being removed
     */
    public void remove(GraphNode g) {
        // Base case is when the priority queue is empty which means there is nothing to delete
        if (this.head == null) {
            return;
        }

        // Next case is when the head node is node being removed
        if (this.head.getNode().equals(g)) {
            this.head = this.head.getNext();

            // Removes from the map
            this.map.remove(g);
            size--;
            return;
        }

        PriorityQueueNode curr = head;
        
        // Traverses through the priority queue to find node before the one being deleted
        while (curr.getNext() != null && !curr.getNext().getNode().equals(g)) {
            curr = curr.getNext();
        }

        // If node is found, we remove the node by updating the next pointer
        if (curr.getNext() != null) {
            curr.setNext(curr.getNext().getNext());
            // Removes from the map
            this.remove(g);
            size--;
        }
    }
    
    /**
     * This method returns and removes the GraphNode with the highest priority in the queue.
     * 
     * @return the GraphNode with the highest priority in the queue
     */
    public GraphNode pullHighestPriorityElement() {
        // Base case is when the priority queue is empty
        if (this.head == null) {
            return null;
        }
        
        // Stores the graph node of the highest priority element which is the head
        GraphNode highestPriorityElement = this.head.getNode();
        
        // Sets the new head as the node after the curr head
        this.head = this.head.getNext();

        // Removes from the map
        this.map.set(highestPriorityElement, -1);
        size--;

        return highestPriorityElement;
    }

    /**
     * This method resizes the capacity of the priority queue.
     */
    public void resize() {
        this.capacity *= 2;
        HashMap newMap = new HashMap(capacity);
        PriorityQueueNode curr = this.head;
        int index = 0;
        this.size = 0;

        // Iterates through the old map and sets the old nodes
        while (curr != null) {
            newMap.set(curr.getNode(), index);
            curr = curr.getNext();
            index++;
        }

        // Sets the new map
        this.map = newMap;
    }

    /**
     * This method rebalances the priority queue after updating the priority of a GraphNode.
     * 
     * @param g is the GraphNode that needs to be rebalanced
     */
    public void rebalance(GraphNode g) {
        remove(g);
        insert(g);
    }

    /**
     * This method checks if the priority queue contains a GraphNode.
     * 
     * @param g is the GraphNode we are checking for
     * @return a boolean representation if the queue contains the node
     */
    public boolean contains(GraphNode g) {
        return this.map.hasKey(g);
    }

    /**
     * This method returns a boolean representation if the queue is empty or not.
     * 
     * @return a boolean representation if the queue is empty or not
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * This method returns the capacity of the hashmap for testing purposes
     *
     * @return the capacity
     */
    public int getCapacity() {
        return this.capacity;
    }
}