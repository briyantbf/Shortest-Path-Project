/**
 * This class represents a key-value pair to be used for the HashMap. It stores a GraphNode as the
 * key and an integer as the value.
 * Known Bugs: None
 *
 * @author Briyant Boakye Frimpong
 * briyantbf@brandeis.edu
 * December, 2024
 * COSI 21A PA3
 */

package main;

public class Entry {
    private GraphNode key;
    private int value;
    private boolean isDeleted;

    /**
     * This is the constructor method which makes a new entry with a key-value pair.
     * 
     * @param key is the GraphNode being used
     * @param value is the integer value linked with the key
     */
    public Entry(GraphNode key, int value) {
        this.key = key;
        this.value = value;
        this.isDeleted = false;
    }

    /**
     * This method sets the value for the entry
     * 
     * @param value is the new integer value linked with the key
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * This method sets the boolean for if the entry was deleted
     * 
     * @param isDeleted is the boolean representation of if an entry was deleted
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method returns the key for the entry.
     * 
     * @return the GraphNode key
     */
    public GraphNode getKey() {
        return key;
    }

    /**
     * This method returns the value for the entry.
     * 
     * @return the integer value
     */
    public int getValue() {
        return value;
    }

    /**
     * This method returns the boolean for if an entry was deleted.
     * 
     * @return a boolean representation
     */
    public boolean getIsDeleted() {
        return this.isDeleted;
    }
}