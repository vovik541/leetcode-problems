package dev.vkh.solutions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

  public static int sumCounts(List<Integer> nums) {
    int result = 0;

    for (int left = 0; left < nums.size(); left++) {
      Set<Integer> distinctElements = new HashSet<>();

      for (int right = left; right < nums.size(); right++) {
        distinctElements.add(nums.get(right));

        int distinctCount = distinctElements.size();
        result += distinctCount * distinctCount;
      }
    }

    return result;
  }

  static void main() {
    System.out.println(sumCounts(List.of(1, 2, 1))); // 15
    System.out.println(sumCounts(List.of(1, 1))); // 3
    System.out.println(sumCounts(List.of(1, 2, 3))); // 20
    System.out.println(sumCounts(List.of(5))); // 1
  }
}
