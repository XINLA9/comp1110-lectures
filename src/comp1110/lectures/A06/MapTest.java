package comp1110.lectures.A06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class MapTest {
    abstract <K, V> Map<K, V> createMap();

    @Test
    void growing() {
        Map<String, Integer> map = createMap();
        Assertions.assertEquals(0, map.size());
        map.put("house", 1000);
        Assertions.assertEquals(1, map.size());
        map.put("rake", 50);
        Assertions.assertEquals(2, map.size());
        map.put("bike", 30);
        Assertions.assertEquals(3, map.size());
        map.put("music", -10);
        Assertions.assertEquals(4, map.size());
    }

    @Test
    void updating() {
        Map<String, Integer> map = createMap();
        Assertions.assertNull(map.put("house", 1000));
        Assertions.assertNull(map.put("rake", 50));
        Assertions.assertNull(map.put("bike", 30));
        Assertions.assertNull(map.put("music", -10));
        Assertions.assertEquals(4, map.size());
        Assertions.assertEquals(50, map.put("rake", -5));
        Assertions.assertEquals(4, map.size());
    }

    @Test
    void getting() {
        Map<String, Integer> map = createMap();
        Assertions.assertNull(map.put("house", 1000));
        Assertions.assertEquals(1000, map.get("house"));
        Assertions.assertNull(map.put("rake", 50));
        Assertions.assertEquals(50, map.get("rake"));
        Assertions.assertNull(map.put("bike", 30));
        Assertions.assertEquals(30, map.get("bike"));
        Assertions.assertNull(map.put("music", -10));
        Assertions.assertEquals(-10, map.get("music"));
        Assertions.assertEquals(50, map.put("rake", -5));
        Assertions.assertEquals(1000, map.get("house"));
        Assertions.assertEquals(-5, map.get("rake"));
        Assertions.assertEquals(30, map.get("bike"));
        Assertions.assertEquals(-10, map.get("music"));
    }

    @Test
    void notIn() {
        Map<String, Integer> map = createMap();
        Assertions.assertNull(map.get("house"));
        Assertions.assertNull(map.put("house", 1000));
        Assertions.assertNull(map.get("mouse"));
        Assertions.assertEquals(1, map.size());
        Assertions.assertNull(map.remove("mouse"));
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(1000, map.remove("house"));
        Assertions.assertEquals(0, map.size());
        Assertions.assertNull(map.remove("house"));
        Assertions.assertEquals(0, map.size());
    }

    @Test
    void removing() {
        Map<String, Integer> map = createMap();
        map.put("house", 1000);
        map.put("rake", 50);
        map.put("bike", 30);
        map.put("music", -10);
        Assertions.assertEquals(4, map.size());
        Assertions.assertEquals(30, map.remove("bike"));
        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals(1000, map.remove("house"));
        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals(50, map.remove("rake"));
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(-10, map.remove("music"));
        Assertions.assertEquals(0, map.size());
    }

    @Test
    void nullArguments() {
        Map<String, Integer> map = createMap();
        map.put("house", 1000);
        Assertions.assertThrows(IllegalArgumentException.class, () -> map.put(null, 10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> map.put("kitty", null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> map.put(null, null));

        Assertions.assertThrows(IllegalArgumentException.class, () -> map.get(null));

        Assertions.assertThrows(IllegalArgumentException.class, () -> map.remove(null));
    }
}
