/**
 * This class implements a hashmap using an array and linear probing to handle collisions. It
 * stores key-value pairs where the key is a GraphNode and the value is an integer.
 * Known Bugs: None
 *
 * @author Briyant Boakye Frimpong
 * briyantbf@brandeis.edu
 * December, 2024
 * COSI 21A PA3
 */

package main;

public class HashMap {
    private Entry[] table;
    private int capacity;
    private int size;

    /**
     * This is the constructor method which initializes components of the hash map such as the
     * table, user-defined capacity, and size .
     * 
     * @param capacity is the size of the hashmap
     */
    public HashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.table = new Entry[this.capacity];
    }

    /**
     * This is the constructor method which initializes components of the hash map such as the
     * table, capacity of 10, and size.
     */
    public HashMap() {
        this.capacity = 10;
        this.size = 0;
        this.table = new Entry[this.capacity];
    }

    /**
     * This method computes the hash index given a GraphNode key using the hash function below.
     * 
     * @param key is the GraphNode key we are computing a hash index for
     * @return the hash index 
     */
    public int hash(GraphNode key) {
        int hashCode = key.getId().hashCode();
        int primeNumber = 61;
        int hashIndex = (hashCode * primeNumber) % this.capacity;

        // Adjusts the hash index in the case that it is negative
        if (hashIndex < 0) {
            hashIndex += capacity;
        }

        return hashIndex;
    }

    /**
     * This method check the hashmap to see if there is an Entry for the GraphNode “key”, if there
     * is, change its value to “value”, otherwise, add it to the hashmap with that value.
     * 
     * @param key is the GraphNode key we are checking for
     * @param value is the new value
     */
    public void set(GraphNode key, int value) {
        // Rehashes the hashmap if necessary
        rehash();
        
        // Gets the index by hasing the key
        int index = hash(key);

        // Finds the correct index to set the entry
        while (this.table[index] != null && !this.table[index].getKey().equals(key) && !this.table[index].getIsDeleted()) {
            index = (index + 1) % this.capacity; // Moves to the next index through linear probing
        }

        // Sets the entry in the hashmap
        if (this.table[index] == null || this.table[index].getIsDeleted()) {
            this.table[index] = new Entry(key, value);
            this.size++;
        }
        else {
            // Updates the value of the entry
            this.table[index].setValue(value);
        }
    }

    /**
     * This method gets the value for the entry with g as the key.
     * 
     * @param g is the key we are getting the value for
     * @return the value for the entry with g as the key
     */
    public int getValue(GraphNode g) {
        // Gets the index by hasing the key
        int index = hash(g);

          // Linear probing to find the key
          while (this.table[index] != null) {
            if (this.table[index].getKey().equals(g) && !this.table[index].getIsDeleted()) {
                return table[index].getValue();
            }
            index = (index + 1) % this.capacity; // Moves to the next index through linear probing
        }

        // Returns -1 if the key isn't found
        return -1;
    }

    /**
     * This method returns a boolean representation if the hashmap has the key g.
     * 
     * @param g is the key we are checking
     * @return a boolean representation if the hashmap has the key g
     */
    public boolean hasKey(GraphNode g) {
        // Gets the index by hasing the key
        int index = hash(g);

        // Linear probing to find the key
        while (this.table[index] != null) {
            if (this.table[index].getKey().equals(g) && !this.table[index].getIsDeleted()) {
                return true;
            }
            index = (index + 1) % this.capacity; // Moves to the next index through linear probing
        }

        return false;
    }

    /**
     * This method removes the entry of the given key from the hash map. It marks the entry as
     * deleted using a boolean instead of removing it completely.
     * 
     * @param key is the GraphNode key we are checking for
     * @return a boolean representation on if the key was found and deleted or not
     */
    public boolean remove(GraphNode key) {
        int index = hash(key);

        // Finds the correct index to delete the entry
        while (this.table[index] != null) {
            if (this.table[index].getKey().equals(key) && !this.table[index].getIsDeleted()) {
                this.table[index].setIsDeleted(true); // Mark the entry as deleted
                this.size--;
                return true; // Key was found and removed
            }
            index = (index + 1) % this.capacity; // Moves to the next index through linear probing
        }

        return false; // Key wasn't found
    }

    /**
     * This method returns the size of the hashmap.
     * 
     * @return the size
     */
    public int size() {
        return this.size;
    }

    /**
     * This method returns the capacity of the hashmap for testing purposes.
     *
     * @return the capacity
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * This method rehashes our hashmap if the load factor exceeds 0.5.
     */
    public void rehash() {
        if ((double) this.size / this.capacity > 0.5) {
            this.capacity *= 2;  // Doubles the capacity
            Entry[] oldTable = this.table;
            this.table = new Entry[this.capacity];
            this.size = 0; // Resets size to 0

            // Traverses through old hashmap and rehashes all the entries while updating the size
            for (Entry entry : oldTable) {
                if (entry != null && !entry.getIsDeleted()) {
                    set(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
