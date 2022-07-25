package comp1110.lectures.A05;

import comp1110.lectures.A04.Set;

public class TreeSet<T> implements Set<T> {
    private class Node {
        Node left;
        Node right;
        T value;

        Node(T value) {
            this.value = value;
        }

        boolean add(T value) {
            if (this.value.equals(value)) {
                return false;
            }
            if (this.value.hashCode() <= value.hashCode()) { // left branch
                if (left == null) {
                    left = new Node(value);
                    return true;
                }
                return left.add(value);
            } else { // right branch
                if (right == null) {
                    right = new Node(value);
                    return true;
                }
                return right.add(value);
            }
        }

        void addNode(Node node) {
            if (node != null) {
                add(node.value);
                addNode(node.left);
                addNode(node.right);
            }
        }

        /**
         *
         * @param value
         * @param remove removes subtree if not the root node
         * @return
         */
        Node get(T value, boolean remove) {
            if (this.value.equals(value)) {
                return this;
            }
            if (this.value.hashCode() <= value.hashCode()) { // left branch
                if (left == null) {
                    return null;
                }
                if (left.value.equals(value)) {
                    var l = left;
                    if (remove) {
                        left = null;
                    }
                    return l;
                }
                return left.get(value, remove);
            } else { // right branch
                if (right == null) {
                    return null;
                }
                if (right.value.equals(value)) {
                    var r = right;
                    if (remove) {
                        right = null;
                    }
                    return r;
                }
                return right.get(value, remove);
            }
        }
    }

    private Node root;
    private int size;

    @Override
    public boolean add(T value) {
        if (value == null) {
            return false;
        }
        if (root == null) {
            root = new Node(value);
            size += 1;
            return true;
        }
        if (root.add(value)) {
            size += 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(T value) {
        if (value == null || root == null) {
            return false;
        }
        var subTree = root.get(value, true);
        if (subTree == null) {
            return false;
        }
        if (subTree == root) {
            root = subTree.left;
            if (subTree.left == null) {
                root = subTree.right;
            } else {
                root.addNode(subTree.right);
            }
        } else {
            root.addNode(subTree.left);
            root.addNode(subTree.right);
        }
        size -= 1;
        return true;
    }

    @Override
    public boolean contains(T value) {
        if (value == null || root == null) {
            return false;
        }
        return root.get(value, false) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<T> newInstance() {
        return new TreeSet<T>();
    }
}
