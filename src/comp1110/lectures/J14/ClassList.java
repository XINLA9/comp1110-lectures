package comp1110.lectures.J14;

import comp1110.lectures.O02.COMP1110Student;
import comp1110.lectures.Playground.Array;

import java.util.*;

public class ClassList {
    public static void main(String[] args) {
        var stuArray = new COMP1110Student[]{
                new COMP1110Student("Mowgli", 7, "u1234568", 4, 20, 3, 0, 3, 20),
                new COMP1110Student("Baloo", 15, "u1274538", 4, 20, 3, 5, 3, 30),
                new COMP1110Student("Bagheera", 20, "u2334568", 4, 25, 3, 5, 3, 40),
                new COMP1110Student("Akela", 17, "u5233383", 4, 30, 4, 5, 4, 48),
        };

        List<COMP1110Student> stuList = new ArrayList<>(Arrays.asList(stuArray));
        // Could do any of these:
        //ArrayList<COMP1110Student> stuList = new ArrayList<>(Arrays.asList(stuArray));
        //var stuList = new ArrayList<>(Arrays.asList(stuArray));
        // Implements list interface, but runtime exception on any array resize operations ("backed" by regular Array)
        //List<COMP1110Student> stuList = Arrays.asList(stuArray);

        stuList.add(new COMP1110Student("Kaa", 17, "u4233383", 4, 30, 4, 5, 4, 48));
        stuList.add(3, new COMP1110Student("Shere Khan (tiger)", 17, "u4233483", 4, 30, 4, 5, 4, 48));
        stuList.remove(3);
        System.out.println("getting the index 2 item: " + stuList.get(2));

        for (var stu : stuList) {
            System.out.println(stu);
        }

        // Converting back to Array
        //Object[] stuArrayNew = stuList.toArray(); // an array of objects
        COMP1110Student[] stuArrayNew = stuList.toArray(new COMP1110Student[0]); // an array of COMP1110Students now!

        List<COMP1110Student> stuList2 = new ArrayList<>(10); // normally don't specify initialCapacity
        // not equivalent to new COMP1110Student[10]
        System.out.println("stuList2 has size: " + stuList2.size());


        Map<String, COMP1110Student> stuMap = new HashMap<>();

        stuMap.put("mow", stuArray[0]);
        stuMap.put("bag", stuArray[1]);
        var ret = stuMap.put("bag", stuArray[2]);
        System.out.println("We replaced: " + ret);
        stuMap.put("bal", stuArray[1]);

        System.out.println("stuMap, keys (and values):");
        for (var k : stuMap.keySet()) {
            System.out.println("" + k + " " + stuMap.get(k));
        }
        System.out.println("stuMap, values:");
        for (var k : stuMap.values()) {
            System.out.println(k);
        }
        System.out.println("stuMap, entries:");
        for (var e : stuMap.entrySet()) {
            System.out.println("" + e.getKey() + " " + e.getValue());
        }
        stuMap.forEach((k, v) -> System.out.println("" + k + " " + v));

        // Sorting List
        // COMP1110Student has not "natural" ordering defined on it
        //Collections.sort(stuList);
        Collections.sort(stuList, (v1, v2) -> v1.getName().compareTo(v2.getName()));
        System.out.println("Ordered list by name");
        stuList.forEach(v -> System.out.println(v));
        Collections.sort(stuList, (v1, v2) -> -((Integer) v1.mark()).compareTo(v2.mark()));
        System.out.println("Ordered list by mark");
        stuList.forEach(v -> System.out.println(v));
    }
}
