package comp1110.lectures.A01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class ListTest {
    public abstract <T> List<T> createList();

    @Test
    void adding() {
        // better name for this test could be "growing" as it partially tests add() and size().
        List<Integer> list = createList();
        Assertions.assertEquals(0, list.size());
        list.add(3);
        Assertions.assertEquals(1, list.size());
        list.add(6);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void getting() {
        List<Integer> list = createList();
        list.add(3);
        Assertions.assertEquals(3, list.get(0));
        list.add(6);
        Assertions.assertEquals(3, list.get(0));
        Assertions.assertEquals(6, list.get(1));
        Assertions.assertEquals(6, list.get(1));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void getOutOfBounds() {
        List<Integer> list = createList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add(3);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void removing() {
        List<Integer> list = createList();
        list.add(3);
        list.add(6);
        list.add(9);
        Assertions.assertEquals(3, list.remove(0));
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(6, list.get(0));
        Assertions.assertEquals(9, list.get(1));
        Assertions.assertEquals(9, list.remove(1));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(6, list.get(0));
    }

    @Test
    void removeOutOfBounds() {
        List<Integer> list = createList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        list.add(3);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    void reversing() {
        List<Integer> list = createList();
        list.add(3);
        list.add(9);
        list.add(6);
        list.add(5);
        list.reverse();
        Assertions.assertEquals(5, list.get(0));
        Assertions.assertEquals(6, list.get(1));
        Assertions.assertEquals(9, list.get(2));
        Assertions.assertEquals(3, list.get(3));

        list.add(100);
        list.reverse();
        Assertions.assertEquals(100, list.get(0));
        Assertions.assertEquals(3, list.get(1));
        Assertions.assertEquals(9, list.get(2));
        Assertions.assertEquals(6, list.get(3));
        Assertions.assertEquals(5, list.get(4));
    }
}
