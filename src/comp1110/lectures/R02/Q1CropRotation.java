// package comp1110.exam;
package comp1110.lectures.R02;

import java.util.*;

/**
 * COMP1110 Final Exam, Question 1.3
 */
public class Q1CropRotation {
    /**
     * Each Vegetable is assigned to one of four groups.
     */
    public enum Group {
        LEGUME, BRASSICA, ALLIUM, FRUITING
    }

    public static class Vegetable {
        String name;
        Group group;

        public Vegetable(String name, Group group) {
            this.name = name;
            this.group = group;
        }

        @Override
        public String toString() {
            return name + " (" + group.name().toLowerCase() + ")";
        }
    }

    /**
     * Get all valid crop rotations that can be composed from the provided
     * set of vegetable crops for the given number of seasons.
     * A crop rotation is a sequence of vegetable crops to plant.
     * One crop is planted per season, and any crop may be planted at most once.
     * Crops must be planted in order of their Group according to the following
     * rules:
     * - a LEGUME may only be followed by a BRASSICA;
     * - a BRASSICA may only be followed by an ALLIUM;
     * - an ALLIUM may only be followed by a FRUITING crop; and
     * - a FRUITING crop may only be followed by a LEGUME.
     * <p>
     * For example, the call
     * getAllRotations([Tomato (fruiting), Onion (allium)], 2)
     * returns a set containing a single rotation:
     * - [Onion (allium), Tomato (fruiting)]
     * because an ALLIUM may only be followed by a fruiting crop.
     * <p>
     * The call
     * getAllRotations([Tomato (fruiting), Kale (brassica), Onion (allium), Pea (legume)], 4)
     * returns a set containing four rotations:
     * - [Kale (brassica), Onion (allium), Tomato (fruiting), Pea (legume)]
     * - [Onion (allium), Tomato (fruiting), Pea (legume), Kale (brassica)]
     * - [Pea (legume), Kale (brassica), Onion (allium), Tomato (fruiting)]
     * - [Tomato (fruiting), Pea (legume), Kale (brassica), Onion (allium)]
     * <p>
     * If no valid crop rotation can be found, an empty list is returned.
     * For example, the call:
     * getAllRotations([Tomato (fruiting), Gai Lan (brassica)], 2)
     * returns an empty set, because a FRUITING crop cannot be followed by
     * a BRASSICA, and a BRASSICA cannot be followed by a FRUITING crop.
     *
     * @param crops   the set of vegetable crops from which to construct a rotation
     * @param seasons the number of seasons (a non-negative integer)
     * @return the set of all possible rotations of the provided crops for the
     * given number of seasons
     */
    public static Set<List<Vegetable>> getAllRotations(Set<Vegetable> crops, int seasons) {
        var rotations = new HashSet<List<Vegetable>>();
        // tests require us to return an empty set, rather than a set containing
        // an empty sequence, when the length parameter is zero; this is not mentioned
        // in the javadoc above, and doesn't really make sense, but we will do what
        // the test asks.
        if (seasons == 0) {
            return rotations;
        }
        var current = new ArrayList<Vegetable>();
        allRotationsSub(current, crops, seasons, rotations);
        return rotations;
    }

    static void allRotationsSub(List<Vegetable> current, Set<Vegetable> crops, int len, Set<List<Vegetable>> result) {
        // current is a permutation (list) under construction;
        // if current.size() == len, then it is finished; add to result
        if (current.size() == len) {
            result.add(new ArrayList<Vegetable>(current));
        }
        else {
            for (var veg : crops)
                if (!current.contains(veg)) {
                    // check that veg can follow last elem in current according to
                    // the rules; if not, then skip it
                    boolean isOk = true;
                    if (current.size() > 0) {
                        Vegetable last = current.get(current.size() - 1);
                        switch (last.group) {
                            case LEGUME:
                                if (veg.group != Group.BRASSICA) // legume can only be followed by brassica
                                    isOk = false;
                                break;
                            case BRASSICA:
                                if (veg.group != Group.ALLIUM) // brassica can only be followed by allium
                                    isOk = false;
                                break;
                            case ALLIUM:
                                if (veg.group != Group.FRUITING) // allium can only be followed by fruiting
                                    isOk = false;
                                break;
                            case FRUITING:
                                if (veg.group != Group.LEGUME) // fruiting can only be followed by legume
                                    isOk = false;
                        }
                    }
                    if (isOk) {
                        current.add(veg);
                        allRotationsSub(current, crops, len, result);
                        current.remove(current.size() - 1);
                    }
                }
        }
    }

    /*
     * Code below is two implementations of methods that generate all possible
     * permutations of a given set of vegetables (and a given size). These were
     * written to illustrate the process of recursive problem solving.
     */
    static void allPermSub(List<Vegetable> current, Set<Vegetable> crops, int len, Set<List<Vegetable>> result) {
        // current is a permutation under construction;
        // if urrent.size() == len, then it is finished; add to result
        if (current.size() == len) {
            result.add(new ArrayList<Vegetable>(current));
        }
        else {
            for (var veg : crops)
                if (!current.contains(veg)) {
                    // check that veg can follow last elem in current according to
                    // the rules; if not, then skip it
                    current.add(veg);
                    allPermSub(current, crops, len, result);
                    current.remove(current.size() - 1);
                }
        }
    }

    public static Set<List<Vegetable>> allPermutations2(Set<Vegetable> crops, int len) {
        var current = new ArrayList<Vegetable>();
        var perms = new HashSet<List<Vegetable>>();
        allPermSub(current, crops, len, perms);
        return perms;
    }

    public static Set<List<Vegetable>> allPermutations1(Set<Vegetable> crops, int len) {
        // pick an element "first" from the set
        // find all permutations of set \ {first} (with len - 1)
        // prepend "first" to all of these permutations
        // repeat for every choice of "first"
        if (crops.isEmpty() || len == 0) {
            var perm = new ArrayList<Vegetable>();
            var perms = new HashSet<List<Vegetable>>();
            perms.add(perm);
            return perms;
        }
        Set<List<Vegetable>> result = new HashSet<>();
        for (var first : crops) {
            var subset = new HashSet<Vegetable>();
            for (var other : crops)
                if (other != first)
                    subset.add(other);
            var sub_perms = allPermutations1(subset, len - 1);
            for (var perm : sub_perms) {
                perm.add(0, first);
                result.add(perm);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        var veggies = new HashSet<Vegetable>();
        veggies.add(new Vegetable("carrot", Group.ALLIUM));
        veggies.add(new Vegetable("cabbage", Group.LEGUME));
        veggies.add(new Vegetable("broccoli", Group.BRASSICA));
        // select allPermutations1 or allPermutations2 to test the two implementations
        var res = allPermutations2(veggies, 2);
        for (var perm : res) {
            StringJoiner j = new StringJoiner(", ");
            for (var x : perm)
                j.add(x.toString());
            System.out.println("permutation:" + j.toString());
        }
    }
}
