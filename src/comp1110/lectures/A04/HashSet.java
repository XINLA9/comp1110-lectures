package comp1110.lectures.A04;

import java.util.LinkedList;

public class HashSet<T> implements Set<T> {
    private static final int INIT_TABLE_SIZE = 4;
    private LinkedList<T>[] table;
    private int size;

    public HashSet(int tableSize) {
        this.table = new LinkedList[tableSize];
    }

    public HashSet() {
        this(INIT_TABLE_SIZE);
    }

    private LinkedList<T> getBucket(T value) {
        var hash = value.hashCode();
        var index = Math.abs(hash) % table.length;
        if (table[index] == null) { // Not ideal side-effect on calls to remove / contains
            table[index] = new LinkedList<>();
        }
        return table[index];
    }

    @Override
    public boolean add(T value) {
        if (value == null) {
            return false;
        }
        var list = getBucket(value);
        if (list.contains(value)) {
            return false;
        }
        list.add(value);
        size += 1;
        return true;
    }

    @Override
    public boolean remove(T value) {
        if (value == null) {
            return false;
        }
        var list = getBucket(value);
        if (list.remove(value)) {
            size -= 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        if (value == null) {
            return false;
        }
        var list = getBucket(value);
        return list.contains(value);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<T> newInstance() {
        return new HashSet<T>();
    }
}
