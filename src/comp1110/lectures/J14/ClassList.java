package comp1110.J14;

import comp1110.O02.COMP1110Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassList {
    public static void main(String[] args) {
        var stuArray = new COMP1110Student[]{
                new COMP1110Student("Mowgli",7,"u1234567",4,20,3,0,3,20),
                new COMP1110Student("Baloo",15,"u1274567",4,20,3,5,3,30),
                new COMP1110Student("Bagheera",20,"u1234567",4,25,3,5,3,40),
                new COMP1110Student("Akela",17,"u1234567",4,30,4,5,4,48)
        };

        List<COMP1110Student> stuList = new ArrayList<>(Arrays.asList(stuArray));

        for(var s : stuList){
            System.out.println(s);
        }
        System.out.println();
        stuList.add(new COMP1110Student("Kaa",17,"u1434567",4,30,4,5,4,48));
        for(var s : stuList){
            System.out.println(s);
        }

        System.out.println();
        stuList.add(3, new COMP1110Student("Kaa",17,"u1434567",4,30,4,5,4,48));
        for(var s : stuList){
            System.out.println(s);
        }

        stuList.remove(3);

        COMP1110Student[] stuArrayNew = stuList.toArray(new COMP1110Student[0]);

        COMP1110Student[] stuArrayNew2 = new COMP1110Student[stuList.size()];
        stuList.toArray(stuArrayNew2);

        System.out.println("array representation");
        for(var s : stuArrayNew2){
            System.out.println(s);
        }

        List<COMP1110Student> fixedSizeList = Arrays.asList(stuArray);
        //fixedSizeList.add(new COMP1110Student("Kaa",17,"u1434567",4,30,4,5,4,48));

        List<COMP1110Student> newList = new ArrayList<>(3);
        System.out.println("NewList has size "+ newList.size());

        System.out.println("Before sorting");
        Collections.sort(stuList,(s1,s2)-> s1.getName().compareTo(s2.getName()));
        System.out.println("After sorting");
        stuList.forEach(s-> System.out.println(s));
        Collections.sort(stuList,(s1,s2)-> ((Integer) s1.mark()).compareTo((Integer) s2.mark()));
        System.out.println("After sorting by mark");
        stuList.forEach(s-> System.out.println(s));
    }
}
