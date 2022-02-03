package antraHW.day12;

import java.util.List;
import java.util.stream.Collectors;

public class Problem1 {
    // Problem 1. create employee class(name / age) create employee list,
    // sort emp list by name(descending), age(ascending), return a new list
    public static List<Employee> sortEmployees(List<Employee> employees){
        return employees
                .stream()
                .sorted((e1, e2) -> {
                    if( e1.getName().equals(e2.getName()) ){
                        return e1.getAge() - e2.getAge();
                    }
                    else{
                        return e2.getName().compareTo(e1.getName());
                    }
                }).collect(Collectors.toList());
    }
}
