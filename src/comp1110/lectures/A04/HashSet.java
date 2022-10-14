package comp1110.lectures.A04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Slightly cleaned up version of the HashSet implementation of the Set ADT
 * (post Friday week 9 lecture). Now uses LinkedList operations (contains,
 * add, remove) consistently.
 */
public class HashSet<T> implements Set<T> {

    LinkedList<T>[] table;
    int n_entries;
    final int DEFAULT_CAPACITY = 20;

    public HashSet() {
        table = initTable(DEFAULT_CAPACITY);
        n_entries = 0;
    }

    /* Util method to create an initialised table (array of linked lists),
    since this is done both in the constructor and the rehash method.
     */
    private LinkedList<T>[] initTable(int size) {
        var table = (LinkedList<T>[]) (new LinkedList[size]);
        for (int i = 0; i < table.length; i++)
            table[i] = new LinkedList<T>();
        return table;
    }

    public int getBucket(T element, int tSize) {
        int h = element.hashCode();
        if (h < 0) h = h * -1;
        return h % tSize;
    }

    /**
     * Add a value to the set if it does not already exist.
     * @param element The value to be added.
     */
    public void add(T element) {
        int bucket = getBucket(element, table.length);
        if (table[bucket].contains(element)) return;
        table[bucket].add(element);
        n_entries += 1;
        rehashIfNeeded();
        checkStats();
    }

    /* Check if table utilisation rate (number of keys stored / table capacity)
    is over a threshold (currently set to 1) and if so, double table capacity and
    rehash all elements.
    */
    private void rehashIfNeeded() {
        double utilrate = n_entries / table.length;
        if (utilrate < 1) return;
        var new_table = initTable(2 * table.length);
        // rehash stored keys into new_table
        for (int b = 0; b < table.length; b++) {
            for (var el : table[b]) {
                int new_b = getBucket(el, new_table.length);
                new_table[new_b].add(el);
            }
        }
        table = new_table;
    }

    public void checkStats() {
        double utilrate = ((double)n_entries) / table.length;
        System.out.println("table utilisation: " + utilrate);
        int maxLen = 0;
        int sumLen = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i].size() > maxLen)
                maxLen = table[i].size();
            sumLen += table[i].size();
        }
        assert (sumLen == n_entries);
        System.out.println("max bucket length: " + maxLen);
        System.out.println("mean bucket length: " + (sumLen/(double)table.length));
    }

    /**
     * Remove the specified value from the set.
     * @param element The value to be removed
     */
    public void remove(T element) {
        int bucket = getBucket(element, table.length);
        if (table[bucket].contains(element)) {
            table[bucket].remove(element);
            n_entries -= 1;
        }
    }

    /**
     * Determine whether the key is in the set
     * @param element The key in question
     * @return True if the key is in the set.
     */
    public boolean contains(T element) {
        int bucket = getBucket(element, table.length);
        return table[bucket].contains(element);
    }

    /**
     * @return the current size of the set.
     */
    public int size() {
        return n_entries;
    }

    /**
     * @return A string representation of the set.
     */
    public String toString() {
        return "";
    }

    /* Test implementation's handling of large number of add operations,
    by reading keys from a file (assumed to be one key per line).
     */
    public static void stressTest(String sourceFile) {
        var set = new HashSet<String>();
        int n_added = 0;
        try {
            FileReader fr = new FileReader(sourceFile);
            final BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                set.add(line);
                n_added += 1;
            }
            fr.close();
            System.out.println(n_added + " names added, set size = " + set.size());
        }
        catch (IOException e) {
            System.out.println("exception " + e + " reading file");
        }
    }

    // main method to call stressTest
    public static void main(String[] args) {
        stressTest("words/plants.txt");
    }

}
