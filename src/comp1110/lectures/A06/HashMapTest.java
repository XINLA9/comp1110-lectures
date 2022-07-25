package comp1110.lectures.A06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashMapTest extends MapTest {
    @Override
    <K, V> Map<K, V> createMap() {
        return new HashMap<K, V>();
    }

    @Test
    void testLambda() {
        var map = new HashMap<String, Integer>();
        map.put("hello", 0);
        map.put("goodbye", 5);
        var mapPlus5 = map.convertWith(v -> v + 5);
        Assertions.assertEquals(5, mapPlus5.get("hello"));
        Assertions.assertEquals(10, mapPlus5.get("goodbye"));
        var array = new int[1];
        array[0] = 15;
        var mapString = mapPlus5.convertWith(v -> {
            array[0] += v;
            return "(" + v + ")";
        });
        Assertions.assertEquals(30, array[0]);
        Assertions.assertEquals("(5)", mapString.get("hello"));
        Assertions.assertEquals("(10)", mapString.get("goodbye"));
    }
}
