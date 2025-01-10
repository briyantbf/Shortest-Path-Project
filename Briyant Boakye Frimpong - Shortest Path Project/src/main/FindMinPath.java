/**
 * This class finds the shortest path from the starting node (home) to the goal node which is where
 * the internship is located (Yahoo building) using Dijkstra's algorithm. The result is written to
 * a file named "answer.txt"
 * Known Bugs: None
 *
 * @author Briyant Boakye Frimpong
 * briyantbf@brandeis.edu
 * December, 2024
 * COSI 21A PA3
 */

package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FindMinPath {

    /**
     * This is the main method which initializes the graph, sets up the priority queue, and finds
     * the shortest path to the goal node using a priority queue.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Initializes the graph wrapper and get the starting node (home)
        GraphWrapper gw = new GraphWrapper(true);
        GraphNode home = gw.getHome();
        home.priority = 0; // Sets the priority of the home node to 0

        // Initialize the min-priority queue and inserts the home node
        MinPriorityQueue queue = new MinPriorityQueue(20);
        queue.insert(home);

        // Stores the goal node as null
        GraphNode goal = null;

        // While loop till queue is empty or goal is found
        while (!queue.isEmpty()) {
            GraphNode curr = queue.pullHighestPriorityElement();
             // Sets curr node as the goal if true
            if (curr.isGoalNode()) {
                goal = curr;
                break;
            }
            // Updates the neighbors of the current node
            updateNeighbors(queue, curr);
        }

         // If the goal is found it writes the path to "answer.txt"
        if (goal != null) {
            try (FileOutputStream fos = new FileOutputStream(new File("answer.txt"))) {
                // Trace the path back from the goal to the home node
                writePath(goal, fos);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            // No path was found
            System.out.println("No path was found to the Yahoo building.");
        }
    }

    /**
     * This is a recursive function to trace the path back from the goal node to the home node.
     * 
     * @param node is the curr node who's previous direction will be written in the file
     * @param fos is the object used to write the directions to the file
     * @throws IOException
     */
    public static void writePath(GraphNode node, FileOutputStream fos) throws IOException {
        if (node.previousNode != null) {
            // Recursively write the path
            writePath(node.previousNode, fos);
        }

        // Ensure that we don't write null values
        if (node.previousDirection != null) {
            // Writes the direction when going backward from home to goal
            String direction = node.previousDirection + "\n";
            fos.write(direction.getBytes()); // Write the direction to the file
        }
    }

    /**
     * This method updates the neighbors of the current node by adding them to the queue with
     * updated priorities.
     * 
     * @param queue is the min-priority queue we're working with
     * @param curr is the node being updated
     */
    public static void updateNeighbors(MinPriorityQueue queue, GraphNode curr) {
        // Checks and processes the north neighbor
        if (curr.hasNorth()) {
            processNeighbor(queue, curr, curr.getNorth(), curr.getNorthWeight(), "North");
        }

        // Check and process the east neighbor
        if (curr.hasEast()) {
            processNeighbor(queue, curr, curr.getEast(), curr.getEastWeight(), "East");
        }

        // Checks and processes the south neighbor
        if (curr.hasSouth()) {
            processNeighbor(queue, curr, curr.getSouth(), curr.getSouthWeight(), "South");
        }
        // Check and process the west neighbor
        if (curr.hasWest()) {
            processNeighbor(queue, curr, curr.getWest(), curr.getWestWeight(), "West");
        }
    }

    /**
     * This method processes a neighbor by updating its priority and previous node/direction if a
     * shorter path is found using Dijkstra's algorithm.
     * 
     * @param queue is the min-priority queue we're working with
     * @param curr is the curr node
     * @param neighbor is the neighbor node being updated
     * @param weight is the distance from curr to neighbor
     * @param direction is the direction from the curr node to neighbor
     */
    public static void processNeighbor(MinPriorityQueue queue, GraphNode curr, GraphNode neighbor, int weight, String direction) {
        // Calculates x/new priority by adding curr priority and distance from curr to neighbor
        int newPriority = curr.priority + weight;

        // Adds the neighbor with the new priority if not in queue
        if (!queue.contains(neighbor)) {
            neighbor.priority = newPriority;
            neighbor.previousNode = curr;
            neighbor.previousDirection = direction;
            queue.insert(neighbor);
        }
        // Updates the priority and reinserts if the neighbor is in the queue but has a shorter path
        else if (newPriority < neighbor.priority) {
            neighbor.priority = newPriority;
            neighbor.previousNode = curr;
            neighbor.previousDirection = direction;
            queue.rebalance(neighbor);
        }
    }
}
