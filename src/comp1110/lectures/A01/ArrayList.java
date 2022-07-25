package comp1110.lectures.A01;

public class ArrayList<T> implements List<T> {
    // Hack to work around limitation of Generics with Arrays
    // Don't do this unless you know what you're doing
    private T[] values = (T[]) new Object[1];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size >= values.length) {
            var tmp = (T[]) new Object[values.length * 2];
            System.arraycopy(values, 0, tmp, 0, values.length);
            values = tmp;
        }
        values[size] = value;
        size += 1;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        return values[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        var val = values[index];
        for (var i = index + 1; i < size; i++) {
            values[i - 1] = values[i];
        }
        values[size - 1] = null;

        size -= 1;
        // Could additionally shrink values according to some rule to avoid wasting memory.
        // Depends on use case if this will be desired behaviour in terms of performance / space trade-off.
        return val;
    }

    @Override
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            int j = size - 1 - i;
            var tmp = values[i];
            values[i] = values[j];
            values[j] = tmp;
        }
    }
}
