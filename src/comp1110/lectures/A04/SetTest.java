package comp1110.lectures.A04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class SetTest {
    public abstract <T> Set<T> createSet();

    @Test
    void duplicates() {
        Set<String> set = createSet();
        Assertions.assertEquals(0, set.size());
        Assertions.assertTrue(set.add("pigeon"));
        Assertions.assertEquals(1, set.size());
        Assertions.assertFalse(set.add("pigeon"));
        Assertions.assertEquals(1, set.size());
    }

    @Test
    void contains() {
        Set<String> set = createSet();
        Assertions.assertEquals(0, set.size());
        Assertions.assertFalse(set.contains(""));
        set.add("pigeon");
        Assertions.assertTrue(set.contains("pigeon"));
        Assertions.assertFalse(set.contains("dog"));
        set.add("cat");
        set.add("house");
        Assertions.assertTrue(set.contains("cat"));
        Assertions.assertTrue(set.contains("house"));
    }

    @Test
    void growing() {
        Set<String> set = createSet();
        Assertions.assertEquals(0, set.size());
        set.add("pigeon");
        Assertions.assertEquals(1, set.size());
        set.add("cat");
        Assertions.assertEquals(2, set.size());
        set.add("house");
        Assertions.assertEquals(3, set.size());
        set.add("banana");
        Assertions.assertEquals(4, set.size());
    }

    @Test
    void removing() {
        Set<String> set = createSet();
        Assertions.assertEquals(0, set.size());
        set.add("pigeon");
        Assertions.assertEquals(1, set.size());
        set.add("cat");
        Assertions.assertEquals(2, set.size());
        set.add("house");
        Assertions.assertEquals(3, set.size());
        set.add("banana");
        Assertions.assertEquals(4, set.size());
        Assertions.assertTrue(set.remove("cat"));
        Assertions.assertFalse(set.contains("cat"));
        Assertions.assertEquals(3, set.size());
        Assertions.assertTrue(set.remove("pigeon"));
        Assertions.assertFalse(set.contains("pigeon"));
        Assertions.assertEquals(2, set.size());
    }

    @Test
    void removingNotInSet() {
        Set<String> set = createSet();
        Assertions.assertEquals(0, set.size());
        set.add("pigeon");
        Assertions.assertEquals(1, set.size());
        set.add("cat");
        Assertions.assertEquals(2, set.size());
        Assertions.assertFalse(set.remove("turkey"));
    }
}
