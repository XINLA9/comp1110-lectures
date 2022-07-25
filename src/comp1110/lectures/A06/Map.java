package comp1110.lectures.A06;

public interface Map<K, V> {
    /**
     * Insert a key, value pair into the map.
     * If the key already exists, overwrite the value.
     * Key and value must be non-null.
     * @param key
     * @param value
     * @return previous value if key already exists, otherwise null
     * @throws IllegalArgumentException if key or value are null
     */
    V put(K key, V value);

    /**
     * Get the value associated with key.
     * @param key
     * @return the value associated with key, otherwise null if key not in map
     * @throws IllegalArgumentException if key is null
     */
    V get(K key);

    /**
     * Removes the key, value pair from the map.
     * @param key
     * @return the value of the pair being removed, otherwise null if key not in map
     * @throws IllegalArgumentException if key is null
     */
    V remove(K key);

    /**
     * @return the number of entries in the map
     */
    int size();
}
