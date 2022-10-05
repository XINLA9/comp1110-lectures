package comp1110.lectures.A01;

public interface List<T> {

	public class InvalidIndex extends RuntimeException {
		int index;
		int size;

		public InvalidIndex(int index, int size) {
			this.index= index;
			this.size = size;
		}

		public String toString() {
			return "InvalidIndex: " + index + " for list of size " + size;
		}
	}
	/*
	 * We would like to specify that any implementation of the List
	 * interface must have a way to create an empty list, but there
	 * does not seem to be any way that java lets us do that: we cannot
	 * specify constructors in the interface, and we cannot add a static
	 * factory method, because it must have an implementation in the
	 * interface (and thus cannot be different for different classes
	 * implementing the interface).
	 */

	/**
	 * Add a value to the tail of the list.
	 * @param value The value to be added.
	 */
	void add(T value);

	/**
	 * Remove the value at the specified index from the list.
	 * List elements are indexed 0 .. length-1.
	 * @param index Index of the value to remove.
	 */
	void remove(int index) throws InvalidIndex;

	/**
	 * @param index
	 * List elements are indexed 0 .. length-1.
	 * @return The value at the specified index.
	 * Method should raise exception if index is negative or outside list size
	 */
	T get(int index) throws InvalidIndex;

	/**
	 * @return the current size of the list.
	 */
	int size();

	/**
	 * Reverse the order of the elements of the list.
	 */
	void reverse();

	/**
	 * @return A string representation of the list.
	 */
	String toString();
}
