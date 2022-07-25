package comp1110.lectures.A04;

public class HashSetTest extends SetTest {
    @Override
    public <T> Set<T> createSet() {
        return new HashSet<T>();
    }
}
