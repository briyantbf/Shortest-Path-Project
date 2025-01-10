Shortest Path Simulation:

Overview:
This project simulates finding the shortest path from a starting point to a destination in a city without a map using Dijkstra’s Algorithm. The goal is to navigate intersections efficiently considering traffic and time costs and generate the route directions as output.

Project Structure: The project contains the following files:

GraphNode Class: Represents intersections in the city, with attributes and methods to navigate and track distances, directions, and neighbors (north, south, east, west).

Min-Priority Queue Class: A heap-based data structure for managing nodes with priorities, supporting operations like insertion, deletion, and rebalancing.

HashMap Class: Implements a mapping of graph nodes to their heap indices, enabling quick lookup and priority management.

Entry Class: Stores key-value pairs (graph nodes and their heap indices) for the HashMap.

Graph Search Algorithm: Implements Dijkstra’s Algorithm in FindMinPath.java to calculate the shortest path and write the route to answer.txt.

Key Functionalities:

GraphNode Class: Methods for neighbor navigation: hasNorth(), getNorth(), and similar for other directions. Unique identification using UUIDs for hashing and indexing.

Min-Priority Queue Class: Efficient priority handling for Dijkstra’s Algorithm.
Operations:
insert(GraphNode g)
pullHighestPriorityElement()
rebalance(GraphNode g)
isEmpty()

HashMap: Custom hash function for UUIDs and collision resolution using closed hashing.
Operations:
set(GraphNode key, int value)
getValue(GraphNode g)
hasKey(GraphNode g)

FindMinPath.java: Implements Dijkstra’s Algorithm with steps to prioritize nodes, update priorities, and trace the shortest path.

Testing and Debugging: Debugging tools allow testing with provided sample files (test_ids.txt and test_edge_weights.txt). Testing includes creating HashMap, heap, and Min-Priority Queue, followed by algorithm validation.
