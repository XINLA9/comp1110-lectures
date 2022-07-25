package comp1110.lectures.A01;

public interface List<T> {
    /**
     * Add a value to end of list.
     * @param value
     */
    void add(T value);

    /**
     * Get value at index position.
     * @param index
     * @return the value
     * @throws IndexOutOfBoundsException
     */
    T get(int index);

    /**
     * The number of elements in the list.
     * @return
     */
    int size();

    /**
     * Remove element at index position, returning it.
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    T remove(int index);

    /**
     * Reverse order of elements in the list.
     */
    void reverse();
}
