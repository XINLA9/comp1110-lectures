package comp1110.lectures.A06;

import comp1110.lectures.A05.TreeSet;

public class TreeMap<K, V> implements Map<K, V> {
    private class Node {
        Node left;
        Node right;
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        V put(K key, V value) {
            if (this.key.equals(key)) {
                var tmp = this.value;
                this.value = value;
                return tmp;
            }
            if (this.key.hashCode() <= key.hashCode()) { // left branch
                if (left == null) {
                    left = new Node(key, value);
                    return null;
                }
                return left.put(key, value);
            } else { // right branch
                if (right == null) {
                    right = new Node(key, value);
                    return null;
                }
                return right.put(key, value);
            }
        }

        void addNode(Node node) {
            if (node != null) {
                put(node.key, node.value);
                addNode(node.left);
                addNode(node.right);
            }
        }

        /**
         *
         * @param key
         * @param remove removes subtree if not the root node
         * @return
         */
        Node get(K key, boolean remove) {
            if (this.key.equals(key)) {
                return this;
            }
            if (this.key.hashCode() <= key.hashCode()) { // left branch
                if (left == null) {
                    return null;
                }
                if (left.key.equals(key)) {
                    var l = left;
                    if (remove) {
                        left = null;
                    }
                    return l;
                }
                return left.get(key, remove);
            } else { // right branch
                if (right == null) {
                    return null;
                }
                if (right.key.equals(key)) {
                    var r = right;
                    if (remove) {
                        right = null;
                    }
                    return r;
                }
                return right.get(key, remove);
            }
        }
    }

    private Node root;
    private int size;

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (value == null) {
            throw new IllegalArgumentException("value is null");
        }
        if (root == null) {
            root = new Node(key, value);
            size += 1;
            return null;
        }
        var oldValue = root.put(key, value);
        if (oldValue == null) {
            size += 1;
            return null;
        }
        return oldValue;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (root == null) {
            return null;
        }
        var node = root.get(key, false);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (root == null) {
            return null;
        }
        var subTree = root.get(key, true);
        if (subTree == null) {
            return null;
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
        return subTree.value;
    }

    @Override
    public int size() {
        return size;
    }
}
