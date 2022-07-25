package comp1110.lectures.A02;

import comp1110.lectures.A01.List;

public class LinkedList<T> implements List<T> {
    private static class Node<T> {
        Node<T> next;
        T value;

        public Node(T value) {
            this.value = value;
        }
    }
    private Node<T> start;
    private Node<T> end;
    private int size;

    public LinkedList() {
    }

    @Override
    public void add(T value) {
        var node = new Node(value);
        if (end == null) {
            start = node;
        } else {
            end.next = node;
        }
        end = node;
        size += 1;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        var i = 0;
        var node = start;
        while (i < index) {
            node = node.next;
            i += 1;
        }
        return node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        var i = 0;
        Node<T> nodePrev = null;
        var node = start;
        while (i < index) {
            nodePrev = node;
            node = node.next;
            i += 1;
        }
        if (size == 1) {  // single entry
            start = null;
            end = null;
        } else if (index == 0) {  // at start of list with size 2 or more
            start = node.next;
        } else if (index == size - 1) {  // at end of list with size 2 or more
            nodePrev.next = null;
            end = nodePrev;
        } else {  // not at end or start
            nodePrev.next = node.next;
        }
        size -= 1;
        return node.value;
    }

    @Override
    public void reverse() {
        // I refactored some of this to improve clarity.
        Node<T> nodePrev = null;
        Node<T> node = start;
        while (node != null) {
            var next = node.next;
            // .. <- [nodePrev]    [node] -> [next] -> ..
            node.next = nodePrev;
            // .. <- [nodePrev] <- [node]    [next] -> ..
            nodePrev = node;
            node = next;
        }
        // What we originally wrote in lecture:
        //var i = 0;
        //Node<T> node = null;
        //node<t> nodenext = start;
        //while (nodenext != null) {
        //    var tmpnext = nodenext.next;
        //    nodenext.next = node;
        //    node = nodenext;
        //    nodenext = tmpnext;
        //    i += 1;
        //}
        var tmpStart = start;
        start = end;
        end = tmpStart;
    }
}
