package comp1110.lectures.A04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;

public class SetTest {

    private Set<String> makeEmpty() {
		//return null;
		return new HashSet<String>();
    }

    @Test
    public void testCreateAndAdd() {
	Set<String> set = makeEmpty();
	assertEquals(set.size(), 0);
	set.add("carrot");
	set.add("cabbage");
	set.add("broccoli");
	assertEquals(set.size(), 3);
	set.add("carrot");
	set.add("cabbage");
	assertEquals(set.size(), 3);
    }

    @Test
    public void testAddAndContains() {
	Set<String> set = makeEmpty();
	set.add("carrot");
	set.add("cabbage");
	set.add("broccoli");
	set.add("onion");
	set.add("cauliflower");
	set.add("snowpea");
	set.add("rocket");
	// not sure of the spelling so let's add both!
	set.add("zucchini");
	set.add("zuccini");
	set.add("leek");
	assertTrue(set.contains("carrot"));
	assertTrue(set.contains("rocket"));
	assertTrue(set.contains("zuccini"));
	assertFalse(set.contains("tomato")); // is a fruit
	assertFalse(set.contains("eggplant"));
	assertFalse(set.contains("barramundi"));
	assertFalse(set.contains("Carrot")); // case sensitive
    }

    @Test
    public void testRemove() {
	Set<String> set = makeEmpty();
	set.add("carrot");
	set.add("cabbage");
	set.add("broccoli");
	set.add("onion");
	set.add("leek");
	set.add("zucchini");
	assertEquals(set.size(), 6);
	set.remove("carrot");
	assertEquals(set.size(), 5);
	assertFalse(set.contains("carrot"));
	assertTrue(set.contains("cabbage"));
	set.remove("snowpea"); // removing a key not in the set
	assertEquals(set.size(), 5);
    }

//	public void stressTest(String sourceFile) {
//		var set = makeEmpty();
//		int n_added = 0;
//		try {
//			FileReader fr = new FileReader(sourceFile);
//			final BufferedReader br = new BufferedReader(fr);
//			String line;
//			while ((line = br.readLine()) != null) {
//				set.add(line);
//				n_added += 1;
//			}
//			fr.close();
//			System.out.println(n_added + " names added, set size = " + set.size());
//		}
//		catch (IOException e) {
//			System.out.println("exception " + e + " reading file");
//		}
//	}

	public static void main(String[] args) {
	SetTest tester = new SetTest();
	tester.testCreateAndAdd();
	tester.testAddAndContains();
	tester.testRemove();
	// tester.stressTest("resources/words/plants.txt");
	System.out.println("all done");
    }
    
}
