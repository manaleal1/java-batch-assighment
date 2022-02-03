package antraHW.day12;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Problem3 {
    // Problem 3. give array of chars, combine them into string by using stream api
    public static String charToString(char[] chars){
        return Arrays
                .asList(chars)
                .stream()
                .map(String::new)
                .collect(Collectors.joining());
    }

}
