package comp1110.lectures.A06;

import java.util.LinkedList;

public class HashMap<K, V> implements Map<K, V> {
    private class Entry {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private static final int INIT_TABLE_SIZE = 4;
    private LinkedList<Entry>[] table;
    private int size;

    public HashMap(int tableSize) {
        table = new LinkedList[tableSize];
    }

    public HashMap() {
        this(INIT_TABLE_SIZE);
    }

    private LinkedList<Entry> getBucket(K key) {
        var hash = key.hashCode();
        var index = Math.abs(hash) % table.length;
        if (table[index] == null) { // Not ideal side-effect on calls to remove / contains
            table[index] = new LinkedList<>();
        }
        return table[index];
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (value == null) {
            throw new IllegalArgumentException("value is null");
        }
        var list = getBucket(key);
        for (var entry : list) {
            if (entry.key.equals(key)) {
                var tmp = entry.value;
                entry.value = value;
                return tmp;
            }
        }
        list.add(new Entry(key, value));
        size += 1;
        return null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        var list = getBucket(key);
        for (var entry : list) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        var list = getBucket(key);
        var it = list.iterator();
        while (it.hasNext()) {
            var entry = it.next();
            if (entry.key.equals(key)) {
                it.remove();
                size -= 1;
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    interface Convert<V, T> {
        T convert(V value);
    }
    public <T> HashMap<K, T> convertWith(Convert<V, T> conv) {
        var map = new HashMap<K, T>();
        for (var row : table) {
            if (row == null) {
                continue;
            }
            for (var entry : row) {
                map.put(entry.key, conv.convert(entry.value));
            }
        }
        return map;
    }

}
