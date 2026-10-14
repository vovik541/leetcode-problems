package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public static long maxAlternatingSum(int[] nums) {
    long[] squares = new long[nums.length];

    for (int i = 0; i < nums.length; i++) {
      squares[i] = (long) nums[i] * nums[i];
    }

    Arrays.sort(squares);

    int negativeCount = nums.length / 2;
    long result = 0;

    for (int i = 0; i < squares.length; i++) {
      if (i < negativeCount) {
        result -= squares[i];
      } else {
        result += squares[i];
      }
    }

    return result;
  }

  static void main() {
    System.out.println(maxAlternatingSum(new int[] {1, 2, 3})); // 12
    System.out.println(maxAlternatingSum(new int[] {1, -1, 2, -2, 3, -3})); // 16
    System.out.println(maxAlternatingSum(new int[] {5})); // 25
    System.out.println(maxAlternatingSum(new int[] {-4, 2, 1, 3})); // 20
  }
}
