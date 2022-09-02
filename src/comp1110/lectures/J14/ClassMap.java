package comp1110.J14;

import comp1110.O02.COMP1110Student;

import java.util.HashMap;
import java.util.Map;

public class ClassMap {
    public static void main(String[] args) {
        var stuArray = new COMP1110Student[]{
                new COMP1110Student("Mowgli",7,"u1234567",4,20,3,0,3,20),
                new COMP1110Student("Baloo",15,"u1274567",4,20,3,5,3,30),
                new COMP1110Student("Bagheera",20,"u1234567",4,25,3,5,3,40),
                new COMP1110Student("Akela",17,"u1234567",4,30,4,5,4,48)
        };

        Map<String,COMP1110Student> stuMap = new HashMap<>();
        stuMap.put("Mow",stuArray[0]);
        stuMap.put("Bal",stuArray[1]);
        stuMap.put("Bag",stuArray[2]);

        for (var k : stuMap.keySet()){
            System.out.println(k);
        }
        for (var v : stuMap.values()){
            System.out.println(v);
        }
        System.out.println();
        stuMap.put("Ba",stuArray[1]);
        stuMap.put("Ba",stuArray[2]);
        for (var k : stuMap.keySet()){
            System.out.println(k);
        }
        for (var v : stuMap.values()){
            System.out.println(v);
        }
        System.out.println();
        for (var k : stuMap.keySet()){
            System.out.println(" key is "+k+" value is "+stuMap.get(k));
        }

        System.out.println();
        for( var e : stuMap.entrySet()){
            System.out.println(" key is "+e.getKey()+" value is "+e.getValue());
        }

        stuMap.forEach((k,v)->System.out.println(" key is "+k+" value is "+v));
    }
}
