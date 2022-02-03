package antraHW.day12;
/*
 *  homework
 *      1. create employee class(name / age)
 *         create employee list, sort emp list by name(descending), age(ascending), return a new list
 *      2. given list of integer, use stream to split list into List<List> (idx 0: odd list, idx 1: even list)
 *         in one line
 *      3. give array of chars, combine them into string by using stream api
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    //driver code
    public static void main(String[] args) {
        //Data for Problem 1
        List<Employee> employees = new LinkedList<Employee>();
        List<String> names = Arrays.asList("Alice","Jenny","Maria","Bob","Carl","Diane","Erik","Francine","Bob","Alice");
        List<Integer> ages = Arrays.asList(33,36,18,55,43,22,23,29,44,32);
        for(int i = 0; i < names.size(); i++){
            employees.add( new Employee(names.get(i),ages.get(i)) );
        }

        System.out.println( Problem1.sortEmployees(employees) );

        //Data for Problem 2
        System.out.println( Problem2.splitList(Arrays.asList(1,9,0,4,7,4,6,2,8,5,7,7,5,3)) );

        //Data for Problem 3
        char[] chars = new char[]{'H','e','l','l','o',' ','W','o','r','l','d','!'};
        System.out.println( Problem3.charToString(chars) );

    }
}
