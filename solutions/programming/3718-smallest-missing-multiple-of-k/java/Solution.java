package dev.vkh.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public static int missingMultiple(int[] nums, int k) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));

    int missingMultiple = k;

    while (true) {
      if (set.contains(missingMultiple)) {
        missingMultiple += k;
        continue;
      }

      return missingMultiple;
    }
  }

  static void main() {
    System.out.println(missingMultiple(new int[] {8, 2, 3, 4, 6}, 2)); // 10
    System.out.println(missingMultiple(new int[] {1, 4, 7, 10, 15}, 5)); // 5
  }
}
