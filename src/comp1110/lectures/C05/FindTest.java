
package comp1110.lectures.C05;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.random.*;
import java.time.Instant;

class FindTest {
    
    static int unsortedFind(int x, List<Integer> ulist) {
	Integer best = null;
	for (var e : ulist) {
	    if (e == x)
			return e;
	    if (e <= x) {
		if (best == null || e <= best)
		    best = e;
	    }
	}
	if (best == null)
	    return 0;
	return best;
    }

    static int sortedFind(int x, List<Integer> slist) {
		if (slist.get(0) > x)
		    return 0;
		if (slist.get(slist.size() - 1) < x)
	    	return slist.get(slist.size() - 1);
		int lower = 0;
		int upper = slist.size();
		while (upper - lower > 1) {
	    	int mid = (lower + upper) / 2;
	    	if (slist.get(mid) <= x)
				lower = mid;
	    	else
				upper = mid;
		}
		return slist.get(lower);
    }

    public static void main(String[] args) {
		RandomGenerator rng = RandomGenerator.of("Xoroshiro128PlusPlus");
		// increase (e.g., double) LIST_SIZE and run again to see scaling behaviour
		final int LIST_SIZE = 10000;
		final int REPEATS = 100000;
		var ulist = new ArrayList<Integer>();
		for (int i = 0; i < LIST_SIZE; i++)
	    	ulist.add(rng.nextInt());
		//ulist.sort(Comparator.naturalOrder());
		long total_time = 0;
		for (int i = 0; i < REPEATS; i++) {
	    	int x = rng.nextInt();
	    	long t0 = System.nanoTime();
			// sorting the list and searching the sorted list: this does not give
			// an accurate picture of the scaling behaviour of this algorithm,
			// because we need to shuffle the list on each iteration.
	    	//ulist.sort(Comparator.naturalOrder());
	    	//int b = sortedFind(x, ulist);
			// search the unsorted list
	    	int b = unsortedFind(x, ulist);
	    	long t1 = System.nanoTime();
	    	total_time += (t1 - t0);
		}
	
		System.out.println("total time: " + (total_time * 1e-9));
    }

}
