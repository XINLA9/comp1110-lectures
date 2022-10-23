package comp1110.lectures.A06;

import comp1110.lectures.A04.HashSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringJoiner;

public class HashMap<K, V> implements Map<K, V> {

    class KVPair<K, V> {
        K key;
        V value;

        KVPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

//        @Override
//        public boolean equals(Object other) {
//            return key.equals(other);
//        }

        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    LinkedList<KVPair<K,V>>[] table;
    int n_entries;
    final int DEFAULT_CAPACITY = 20;

    public HashMap() {
        table = initTable(DEFAULT_CAPACITY);
        n_entries = 0;
    }

    /* Util method to create an initialised table (array of linked lists),
    since this is done both in the constructor and the rehash method.
     */
    private LinkedList<KVPair<K,V>>[] initTable(int size) {
        var table = (LinkedList<KVPair<K,V>>[]) (new LinkedList[size]);
        for (int i = 0; i < table.length; i++)
            table[i] = new LinkedList<KVPair<K,V>>();
        return table;
    }

    public int getBucket(K key, int tSize) {
        int h = key.hashCode();
        if (h < 0) h = h * -1;
        return h % tSize;
    }

    int index(K key, LinkedList<KVPair<K,V>> list) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).key.equals(key))
                return i;
        return -1;
    }

    /**
     * Add a value to the set if it does not already exist.
     * @param key The key to be added or updated.
     * @param value The associated value.
     */
    public void put(K key, V value) {
        int bucket = getBucket(key, table.length);
        int index = this.index(key, table[bucket]);
        if (index == -1) { // key doesn't exist
            table[bucket].add(new KVPair(key, value));
            n_entries += 1;
//            rehashIfNeeded();
//            checkStats();
        }
        else {
            table[bucket].get(index).value = value;
        }
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
                int new_b = getBucket(el.key, new_table.length);
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
     * @param key The value to be removed
     */
    public void remove(K key) {
        int bucket = getBucket(key, table.length);
        int i = index(key, table[bucket]);
        if (i != -1) {
            table[bucket].remove(i);
            n_entries -= 1;
        }
    }

    /**
     * Determine whether the key is in the set
     * @param key The key in question
     * @return True if the key is in the set.
     */
    public boolean contains(K key) {
        int bucket = getBucket(key, table.length);
        return index(key, table[bucket]) != -1;
    }

    public V get(K key) {
        int bucket = getBucket(key, table.length);
        int index = this.index(key, table[bucket]);
        if (index == -1) return null;
        return table[bucket].get(index).value;
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
        StringJoiner all = new StringJoiner(" ");
        for (int b = 0; b < table.length; b++) {
            for (var pair : table[b])
                all.add(b + ":" + pair.toString());
        }
        return all.toString();
    }

    /* Test implementation's handling of large number of add operations,
    by reading keys from a file (assumed to be one key per line).
     */
//    public static void stressTest(String sourceFile) {
//        var set = new HashSet<String>();
//        int n_added = 0;
//        try {
//            FileReader fr = new FileReader(sourceFile);
//            final BufferedReader br = new BufferedReader(fr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                set.add(line);
//                n_added += 1;
//            }
//            fr.close();
//            System.out.println(n_added + " names added, set size = " + set.size());
//        }
//        catch (IOException e) {
//            System.out.println("exception " + e + " reading file");
//        }
//    }
//
//    // main method to call stressTest
//    public static void main(String[] args) {
//        stressTest("words/plants.txt");
//    }

}
