package comp1110.lectures.A06;

import comp1110.lectures.A05.BSTSet;

public class BSTMap<K extends Comparable<K>, V> implements Map<K,V> {

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        int height() {
            int lh = (left == null ? 0 : left.height());
            int rh = (right == null ? 0 : right.height());
            return (lh < rh ? rh : lh) + 1;
        }

        boolean put(K new_key, V new_value) {
            int c = this.key.compareTo(new_key);
            if (c > 0) { // new_value < this.value; insert into left subtree
                if (left == null) {
                    left = new Node(new_key, new_value);
                    return true;
                }
                else
                    return left.put(new_key, new_value);
            }
            else if (c < 0) { // new_vlaue > this.value: insert into right subtree
                if (right == null) {
                    right = new Node(new_key, new_value);
                    return true;
                }
                else
                    return right.put(new_key, new_value);
            }
            else { // equal: nee_value should equal this.value
                assert this.key.equals(new_key);
                this.value = new_value;
                return false;
            }
        }

        Node find(K new_key) {
            int c = this.key.compareTo(new_key);
            if (c > 0) { // new_value < this.value: new_value should be in left subtree
                if (left == null)
                    return null;
                else
                    return left.find(new_key);
            }
            else if (c < 0) { // new_vlaue > this.value: insert into right subtree
                if (right == null)
                    return null;
                else
                    return right.find(new_key);
            }
            else { // equal: nee_value should equal this.value
                assert this.key.equals(new_key);
                return this;
            }
        }

        void insert(Node root, Node subtree) {
            if (subtree == null) return;
            int c = this.key.compareTo(subtree.key);
            if (c > 0) { // subtree root goes on the left
                Node sub_right = subtree.right;
                subtree.right = null;
                if (left == null)
                    left = subtree;
                else
                    left.insert(root, subtree);
                root.insert(root, sub_right);
            }
            else if (c < 0) { // subtree root goes on the right
                Node sub_left = subtree.left;
                subtree.left = null;
                if (right == null)
                    right = subtree;
                else
                    right.insert(root, subtree);
                root.insert(root, sub_left);
            }
            else { // equal
                if (left == null)
                    left = subtree.left;
                else
                    left.insert(root, subtree.left);
                if (right == null)
                    right = subtree.right;
                else
                    right.insert(root, subtree.right);
            }
        }

        Node remove(K rkey) {
            int c = this.key.compareTo(rkey);
            if (c > 0) { // rvalue is in left subtree (if it exists)
                if (left == null) return this;
                left = left.remove(rkey);
                return this;
            }
            else if (c < 0) { // rvalue is in right subtree (if exists)
                if (right == null) return this;
                right = right.remove(rkey);
                return this;
            }
            else {
                assert this.key.equals(rkey);
                if (right == null) // drop this node, move left up
                    return left;
                if (left == null)
                    return right;
                left.insert(left, right);
                return left;
            }
        }

        public String toString() {
            String sleft = (left == null ? "EMPTY" : left.toString());
            String sright = (right == null ? "EMPTY" : right.toString());
            return "(" + sleft + ", " + this.key.toString() + ":" + this.value.toString() + ", " + sright + ")";
        }

    }

    Node root;
    int n_elements;

    /**
     * Add a value to the set if it does not already exist.
     * @param key The key to be added/updated.
     * @param value The value to store with key.
     */
    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            n_elements = 1;
        }
        if (root.put(key, value))
            n_elements += 1;
        //System.out.println("tree after add(" + element + "): " + this.toString());
    }

    /**
     * Remove the specified value from the set, if it exists.
     * @param key The key to be removed
     */
    public void remove(K key) {
        if (root == null) return;
        Node n = root.find(key);
        if (n != null)
            n_elements -= 1;
        root = root.remove(key);
        //System.out.println("tree after remove(" + element + "): " + this.toString());
    }

    /**
     * Determine whether the key is in the map.
     * @param key The key in question
     * @return True if the key is in the map.
     */
    public boolean contains(K key) {
        if (root == null) return false;
        return root.find(key) != null;
    }

    public V get(K key) {
        if (root == null) return null;
        Node n = root.find(key);
        if (n == null) return null;
        return n.value;
    }

    /**
     * @return the current size of the set.
     */
    public int size() {
        return n_elements;
    }

    /**
     * @return A string representation of the set.
     */
    public String toString() {
        if (root == null) return "EMPTY";
        return root.toString();
    }

}
