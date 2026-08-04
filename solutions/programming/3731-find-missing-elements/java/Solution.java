package dev.vkh.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

  public static List<Integer> findMissingElements(int[] nums) {
    TreeSet<Integer> present =
        Arrays.stream(nums).boxed().collect(Collectors.toCollection(TreeSet::new));

    List<Integer> missingElements = new ArrayList<>();

    for (int min = present.getFirst(), max = present.getLast(); min < max; min++) {
      if (!present.contains(min)) missingElements.add(min);
    }

    return missingElements;
  }

  static void main() {
    System.out.println(findMissingElements(new int[] {1, 4, 2, 5})); // [3]
    System.out.println(findMissingElements(new int[] {7, 8, 6, 9})); // []
    System.out.println(findMissingElements(new int[] {5, 1})); // [2,3,4]
  }
}
