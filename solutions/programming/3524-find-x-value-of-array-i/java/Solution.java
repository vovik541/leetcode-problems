package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public static long[] resultArray(int[] nums, int k) {
    long[] result = new long[k];
    long[] previousRemainderCounts = new long[k];

    for (int number : nums) {
      int currentRemainder = number % k;
      long[] currentRemainderCounts = new long[k];

      currentRemainderCounts[currentRemainder]++;

      for (int previousRemainder = 0; previousRemainder < k; previousRemainder++) {
        int newRemainder = (previousRemainder * currentRemainder) % k;

        currentRemainderCounts[newRemainder] += previousRemainderCounts[previousRemainder];
      }

      for (int remainder = 0; remainder < k; remainder++) {
        result[remainder] += currentRemainderCounts[remainder];
      }

      previousRemainderCounts = currentRemainderCounts;
    }

    return result;
  }

  static void main() {
    System.out.println(Arrays.toString(resultArray(new int[] {1, 2, 3, 4, 5}, 3)));
    // [9, 2, 4]
    System.out.println(Arrays.toString(resultArray(new int[] {1, 2, 4, 8, 16, 32}, 4)));
    // [18, 1, 2, 0]
    System.out.println(Arrays.toString(resultArray(new int[] {1, 1, 2, 1, 1}, 2)));
    // [9, 6]
  }
}
