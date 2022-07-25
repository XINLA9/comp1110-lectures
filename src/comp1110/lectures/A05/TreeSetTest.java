package comp1110.lectures.A05;

import comp1110.lectures.A04.Set;
import comp1110.lectures.A04.SetTest;

public class TreeSetTest extends SetTest {

    @Override
    public <T> Set<T> createSet() {
        return new TreeSet<T>();
    }
}
