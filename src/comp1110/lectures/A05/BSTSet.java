package comp1110.lectures.A05;

import comp1110.lectures.A04.HashSet;
import comp1110.lectures.A04.Set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BSTSet<T extends Comparable<T>> implements Set<T> {

    private class Node {
        T value;
        Node left;
        Node right;

        Node(T value) {
            this.value = value;
        }

        int height() {
            int lh = (left == null ? 0 : left.height());
            int rh = (right == null ? 0 : right.height());
            return (lh < rh ? rh : lh) + 1;
        }

        boolean add(T new_value) {
            int c = this.value.compareTo(new_value);
            if (c > 0) { // new_value < this.value; insert into left subtree
                if (left == null) {
                    left = new Node(new_value);
                    return true;
                }
                else
                    return left.add(new_value);
            }
            else if (c < 0) { // new_vlaue > this.value: insert into right subtree
                if (right == null) {
                    right = new Node(new_value);
                    return true;
                }
                else
                    return right.add(new_value);
            }
            else { // equal: nee_value should equal this.value
                assert this.value.equals(new_value);
                return false;
            }
        }

        boolean contains(T new_value) {
            int c = this.value.compareTo(new_value);
            if (c > 0) { // new_value < this.value: new_value should be in left subtree
                if (left == null)
                    return false;
                else
                    return left.contains(new_value);
            }
            else if (c < 0) { // new_vlaue > this.value: insert into right subtree
                if (right == null)
                    return false;
                else
                    return right.contains(new_value);
            }
            else { // equal: nee_value should equal this.value
                assert this.value.equals(new_value);
                return true;
            }
        }

        void insert(Node root, Node subtree) {
            if (subtree == null) return;
            int c = this.value.compareTo(subtree.value);
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

        Node remove(T rvalue) {
            int c = this.value.compareTo(rvalue);
            if (c > 0) { // rvalue is in left subtree (if it exists)
                if (left == null) return this;
                left = left.remove(rvalue);
                return this;
            }
            else if (c < 0) { // rvalue is in right subtree (if exists)
                if (right == null) return this;
                right = right.remove(rvalue);
                return this;
            }
            else {
                assert this.value.equals(rvalue);
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
            return "(" + sleft + ", " + this.value.toString() + ", " + sright + ")";
        }

    }

    Node root;
    int n_elements;

    /**
     * Add a value to the set if it does not already exist.
     * @param element The value to be added.
     */
    public void add(T element) {
        if (root == null) {
            root = new Node(element);
            n_elements = 1;
        }
        if (root.add(element))
            n_elements += 1;
        //System.out.println("tree after add(" + element + "): " + this.toString());
    }

    /**
     * Remove the specified value from the set.
     * @param element The value to be removed
     */
    public void remove(T element) {
        if (root == null) return;
        if (contains(element))
            n_elements -= 1;
        root = root.remove(element);
        //System.out.println("tree after remove(" + element + "): " + this.toString());
    }

    /**
     * Determine whether the key is in the set
     * @param element The key in question
     * @return True if the key is in the set.
     */
    public boolean contains(T element) {
        if (root == null) return false;
        return root.contains(element);
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

    public static void stressTest(String sourceFile) {
        var set = new BSTSet<String>();
        int n_added = 0;
        try {
            FileReader fr = new FileReader(sourceFile);
            final BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                set.add(line);
                n_added += 1;
                System.out.println("height:"  + set.root.height());
            }
            fr.close();
            System.out.println(n_added + " names added, set size = " + set.size());
        } catch (IOException e) {
            System.out.println("exception " + e + " reading file");
        }
    }

        public static void main(String[] args) {
//        var tree = new BSTSet<String>();
//        tree.add("carol");
//        tree.add("alice");
//        tree.add("bob");
//        tree.add("dave");
//        System.out.println(tree.toString());
            stressTest("words/plants.txt");
    }
}
