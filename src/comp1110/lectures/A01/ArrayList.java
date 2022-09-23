package comp1110.lectures.A01;

public class ArrayList<T> implements List<T> {
    private T[] elements = null;
    int listSize = 0;

    public ArrayList() {
        // done?
    }

     /**
     * Add a value to the tail of the list.
     * @param value The value to be added.
     */
    public void add(T value) {
        // ensure there is an empty space to put the value in!
        if (elements == null) {
            elements = (T[]) (new Object[1]);
        }
        else if (elements.length == listSize) {
            T[] newEls = (T[]) (new Object[elements.length * 2]);
            for (int i = 0; i < elements.length; i++)
                newEls[i] = elements[i];
            elements = newEls;
        }
        elements[listSize] = value;
        listSize += 1;
    }

    /**
     * Remove the value at the specified index from the list.
     * List elements are indexed 0 .. length-1.
     * @param index Index of the value to remove.
     */
    public void remove(int index) {
        // FIXME
    }

    /**
     * @param index
     * List elements are indexed 0 .. length-1.
     * @return The value at the specified index.
     */
    public T get(int index) {
        return elements[index];
    }

    /**
     * @return the current size of the list.
     */
    public int size() {
        return listSize;
    }

    /**
     * Reverse the order of the elements of the list.
     */
    public void reverse() {
        // FIXME
    }

    /**
     * @return A string representation of the list.
     */
    public String toString() {
        return ""; // FIXME
    }

}