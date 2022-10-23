package comp1110.lectures.A06;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapTest {

	Map<String, Integer> makeEmpty() {
		//return null;
		//return new HashMap<String, Integer>();
		return new BSTMap<String, Integer>();
	}

	@Test
	public void testCreateAndAdd() {
		Map<String,Integer> map = makeEmpty();
		assertTrue(map.size() == 0);
		map.put("Alice", 65);
		System.out.println(map.toString());
		assertTrue(map.size() == 1);
		map.put("Bob", 66);
		System.out.println(map.toString());
		assertTrue(map.size() == 2);
		map.put("Bob", 2);
		System.out.println(map.toString());
		assertTrue(map.size() == 2);
	}

	@Test
	public void testPutAndGet() {
		Map<String,Integer> map = makeEmpty();
		map.put("Alice", 2);
		map.put("Bob", 4);
		map.put("Carol", 8);
		assertTrue(map.size() == 3);
		assertTrue(map.get("Bob") == 4);
		map.put("Bob", 16);
		assertTrue(map.get("Carol") == 8);
		assertTrue(map.get("Alice") == 2);
		assertTrue(map.get("Bob") == 16);
	}

	@Test
	public void testRemove() {
		Map<String,Integer> map = makeEmpty();
		map.put("Bob", 19);
		assertTrue(map.size() == 1);
		map.remove("Bob");
		assertTrue(map.size() == 0);
		map.put("Alice", 21);
		assertTrue(map.size() == 1);
		map.put("Carol", 20);
		assertTrue(map.size() == 2);
		map.remove("Alice");
		assertTrue(map.size() == 1);
		assertTrue(map.get("Carol") == 20);
	}
	
}
