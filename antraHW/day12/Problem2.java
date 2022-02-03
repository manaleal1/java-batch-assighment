package antraHW.day12;

import java.util.List;
import java.util.stream.Collectors;

public class Problem2 {
    // Problem 2. given list of integer, use stream to split list into
    // List<List> (idx 0: odd list, idx 1: even list) in one line
    public static List<List<Integer>> splitList(List<Integer> integers){
        return integers
                .stream()
                .collect(Collectors.partitioningBy(integer -> integer%2 == 0))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

}
