package comp1110.lectures.A04;

public interface Set<T> {
    /**
     * @param value the value to add, must not be null
     * @return true if value was added (not already present)
     */
    boolean add(T value);

    /**
     * @param value the value to add, must not be null
     * @return true if value was found and removed
     */
    boolean remove(T value);

    boolean contains(T value);

    int size();

    Set<T> newInstance();
}
