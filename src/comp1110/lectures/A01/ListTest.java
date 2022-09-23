package comp1110.lectures.A01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
public class ListTest {

    public List<Integer> makeEmpty() {
        return new ArrayList<Integer>();
        //return null;
    }

    @Test
    public void testCreateEmpty() {
        List<Integer> a_list = makeEmpty();
        Assertions.assertEquals(0, a_list.size(), "empty list does not have size 0");
        // Trying to get an element from an empty list throws a NullPointerException,
        // because we have not allocated any array (it is null).
        Assertions.assertThrows(java.lang.NullPointerException.class, () -> a_list.get(0));
    }

    @Test
    public void testCreateAndAdd() {
        List<Integer> a_list = makeEmpty();
        a_list.add(1);
        a_list.add(2);
        Assertions.assertEquals(2, a_list.size(), "size not == 2 after adding 2 elements");
        a_list = makeEmpty();
        a_list.add(1);
        a_list.add(1);
        Assertions.assertEquals(2, a_list.size(), "size not == 2 after adding 2 elements");
    }

    @Test
    public void testAddAndRetrieve() {
        List<Integer> a_list = makeEmpty();
        for (int i = 1; i <= 20; i++)
            a_list.add(i);
        for (int i = 0; i < 20; i++)
            Assertions.assertEquals(i + 1, a_list.get(i), "return wrong element");
        // Getting an element outside the list size does not necessarily cause an exception,
        // because the way that we increase the size (doubling capacity when re-allocating)
        // means that the array may be longer than the current value of listSize.
        //Assertions.assertThrows(java.lang.ArrayIndexOutOfBoundsException.class, () -> a_list.get(20));
        // But using a negative index does cause an exception.
        Assertions.assertThrows(java.lang.ArrayIndexOutOfBoundsException.class, () -> a_list.get(-1));
    }

    }
