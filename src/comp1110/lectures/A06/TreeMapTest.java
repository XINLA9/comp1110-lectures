package comp1110.lectures.A06;

public class TreeMapTest extends MapTest {
    @Override
    <K, V> Map<K, V> createMap() {
        return new TreeMap<K, V>();
    }
}
