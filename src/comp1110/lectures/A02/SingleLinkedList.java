package comp1110.lectures.A02;

import comp1110.lectures.A01.List;

public class SingleLinkedList<T> implements List<T> {
    /* single-linked list node */
    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }

    }

    Node first;
    Node last;

    public SingleLinkedList() {
       first = null;
       last = null;
    }
    /**
     * Add a value to the tail of the list.
     * @param value The value to be added.
     */
    public void add(T value) {
        if (last == null) {
            // the list is empty: the new node is both first and last
            first = new Node(value);
            last = first;
        }
        else {
            Node newNode = new Node(value);
            last.next = newNode; // current last node's next becomes new node
            last = newNode; // new node becomes new last in the list
        }
    }

    /**
     * Remove the value at the specified index from the list.
     * List elements are indexed 0 .. length-1.
     * @param index Index of the value to remove.
     */
    public void remove(int index) throws InvalidIndex {
        // special case: the node to remove is first in the list
        if (index == 0) {
            if (first == null) throw new InvalidIndex(index, 0);
            first = first.next;
            return;
        }
        // general case: find the node that precedes the node to remove
        Node pred = findIndex(first, index - 1);
        if (pred == null) throw new InvalidIndex(index, size());
        if (pred.next == null) throw new InvalidIndex(index, size());
        // if the node to remove is last, update last to be the preceding node
        if (pred.next == last)
            last = pred;
        // preceding node's next becomes node-to-remove's next
        pred.next = pred.next.next;
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
