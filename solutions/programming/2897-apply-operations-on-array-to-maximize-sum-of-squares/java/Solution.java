package dev.vkh.solutions;

import java.util.*;

class Solution {

  private static final long MOD = 1_000_000_007L;

  public static int maxSum(List<Integer> nums, int k) {
    int[] bitCounts = new int[30];

    for (int number : nums) {
      for (int bit = 0; bit < 30; bit++) {
        if ((number & (1 << bit)) != 0) {
          bitCounts[bit]++;
        }
      }
    }

    long result = 0;

    for (int i = 0; i < k; i++) {
      long number = 0;

      for (int bit = 0; bit < 30; bit++) {
        if (bitCounts[bit] > 0) {
          number |= 1L << bit;
          bitCounts[bit]--;
        }
      }

      result = (result + number * number) % MOD;
    }

    return (int) result;
  }

  static void main() {
    System.out.println(maxSum(Arrays.asList(2, 6, 5, 8), 2)); // 261
    System.out.println(maxSum(Arrays.asList(4, 5, 4, 7), 3)); // 90
    System.out.println(maxSum(Arrays.asList(1, 2, 3), 2)); // 18
  }
}
