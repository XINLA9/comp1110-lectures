package comp1110.lectures.A01;

import comp1110.lectures.A02.SingleLinkedList;
import comp1110.lectures.A02.DoubleLinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
public class ListTest {

    /**
     * Create and return an empty list. This method determines which list
     * implementation we are testing.
     * @return New empty list
     */
    public List<Integer> makeEmpty() {
        //return new ArrayList<Integer>();
        //return new SingleLinkedList<Integer>();
        return new DoubleLinkedList<Integer>();
        //return null;
    }

    // Returns a list of integers 1,..,n, for testing purposes.
    public List<Integer> makeTestList(int n) {
        List<Integer> a_list = makeEmpty();
        for (int i = 1; i <= n; i++)
            a_list.add(i);
        return a_list;
    }

    @Test
    public void testCreateEmpty() {
        List<Integer> a_list = makeEmpty();
        Assertions.assertEquals(0, a_list.size(), "empty list does not have size 0");
        // Trying to get an element from an empty list throws a NullPointerException,
        // because we have not allocated any array (it is null).
        Assertions.assertThrows(List.InvalidIndex.class, () -> a_list.get(0));
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
        List<Integer> a_list = makeTestList(20);
        for (int i = 0; i < 20; i++)
            Assertions.assertEquals(i + 1, a_list.get(i), "return wrong element");
        // Getting an element outside the list size does not necessarily cause an exception,
        // because the way that we increase the size (doubling capacity when re-allocating)
        // means that the array may be longer than the current value of listSize.
        //Assertions.assertThrows(java.lang.ArrayIndexOutOfBoundsException.class, () -> a_list.get(20));
        // But using a negative index does cause an exception.
        Assertions.assertThrows(List.InvalidIndex.class, () -> a_list.get(-1));
    }

    @Test
    public void testAddAndRemove() {
        List<Integer> a_list = makeTestList(20);
        int i = 0;
        int n_removed = 0;
        while (i < a_list.size()) {
            if (a_list.get(i) % 2 == 1) {
                a_list.remove(i);
                n_removed += 1;
                Assertions.assertEquals(20 - n_removed, a_list.size());
            }
            else
                i += 1;
        }
        Assertions.assertEquals(10, a_list.size());
        for (i = 0; i < 10; i++)
            Assertions.assertEquals(2 * (i + 1), a_list.get(i), "return wrong element");
    }

    public void testReversal(int n) {
        List<Integer> a_list = makeTestList(n);
        a_list.reverse();
        for (int i = 0; i < n; i++)
            Assertions.assertEquals(n - i, a_list.get(i), "return wrong element");
    }

    @Test
    public void testReverse() {
        testReversal(4); // even length
        testReversal(5); // odd length
        testReversal(1); // edge case
        testReversal(0); // edge case
    }

    }
