package comp1110.lectures.A02;

import comp1110.lectures.A01.List;

public class DoubleLinkedList<T> implements List<T> {
    /* double-linked list node */
    private class Node {
        T value;
        Node prev;
        Node next;

        Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }

    }

    Node first;
    Node last;

    public DoubleLinkedList() {
       first = null;
       last = null;
    }
    /**
     * Add a value to the tail of the list.
     * @param value The value to be added.
     */
    public void add(T value) {
        if (last == null) {
            // the list is empty: new node becomes first and last
            first = new Node(value);
            last = first;
        }
        else {
            Node newNode = new Node(value);
            newNode.prev = last; // current last becomes new node's previous
            last.next = newNode; // current last's next becomes new node
            last = newNode; // new node becomes new last
        }
    }

    /**
     * Remove the value at the specified index from the list.
     * List elements are indexed 0 .. length-1.
     * @param index Index of the value to remove.
     */
    public void remove(int index) throws InvalidIndex {
        Node nodeToRemove = findIndex(first, index);
        if (nodeToRemove == null) throw new InvalidIndex(index, size());
        if (nodeToRemove.prev == null) {
            // if nodeToRemove doesn't have a previous, it is first in the list:
            // node's next becomes the new first
            first = nodeToRemove.next;
        }
        else {
            // otherwise, node's previous' next becomes node's next
            nodeToRemove.prev.next = nodeToRemove.next;
        }
        if (nodeToRemove.next == null) {
            // likewise, if nodeToRemove doesn't have a next, it is last in the list
            // node's previous becomes the new last
            last = nodeToRemove.prev;
        }
        else {
            // otherwise, node's previous becomes node's next's previous
            nodeToRemove.next.prev = nodeToRemove.prev;
        }
    }

    /**
     * @param index
     * List elements are indexed 0 .. length-1.
     * @return The value at the specified index.
     * Method should raise exception if index is negative or outside list size
     */
    public T get(int index) throws InvalidIndex {
        if (index < 0) throw new InvalidIndex(index, 0);
        Node found = findIndex(first, index);
        if (found == null) throw new InvalidIndex(index, size());
        return found.value;
    }

    /**
     * Find the node at +index from current node.
     * @param current A node in the list
     * @param index Index relative to current node (>= 0)
     * @return The requested node, or null if the index is invalid
     */
    private Node findIndex(Node current, int index) {
        if (index == 0) return current;
        // otherwise, assume index > 0
        if (current.next == null) return null;
        return findIndex(current.next, index - 1);
    }

    /**
     * @return the current size of the list.
     */
    public int size() {
        Node current = first;
        int count = 0;
        while (current != null) {
            current = current.next;
            count += 1;
        }
        return count;
    }

    /**
     * Reverse the order of the elements of the list.
     */
    public void reverse() {
        // FIXME
    }

    /**
     * @return A string representation of the list.
     */
    public String toString() {
        return ""; // FIXME
    }
}
