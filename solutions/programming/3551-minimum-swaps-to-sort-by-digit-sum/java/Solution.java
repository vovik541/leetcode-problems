package dev.vkh.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

  public static int minSwaps(int[] nums) {
    int length = nums.length;

    Integer[] sorted = new Integer[length];

    for (int i = 0; i < length; i++) {
      sorted[i] = nums[i];
    }

    Arrays.sort(
        sorted,
        (first, second) -> {
          int firstSum = digitSum(first);
          int secondSum = digitSum(second);

          if (firstSum != secondSum) {
            return Integer.compare(firstSum, secondSum);
          }

          return Integer.compare(first, second);
        });

    Map<Integer, Integer> targetIndices = new HashMap<>();

    for (int i = 0; i < length; i++) {
      targetIndices.put(sorted[i], i);
    }

    boolean[] visited = new boolean[length];
    int swaps = 0;

    for (int i = 0; i < length; i++) {
      if (visited[i]) {
        continue;
      }

      int current = i;
      int cycleLength = 0;

      while (!visited[current]) {
        visited[current] = true;

        current = targetIndices.get(nums[current]);
        cycleLength++;
      }

      swaps += cycleLength - 1;
    }

    return swaps;
  }

  private static int digitSum(int number) {
    int sum = 0;

    while (number > 0) {
      sum += number % 10;
      number /= 10;
    }

    return sum;
  }

  static void main() {
    System.out.println(minSwaps(new int[] {37, 100})); // 1
    System.out.println(minSwaps(new int[] {22, 14, 33, 7})); // 0
    System.out.println(minSwaps(new int[] {18, 43, 34, 16})); // 2
    System.out.println(minSwaps(new int[] {1})); // 0
  }
}
