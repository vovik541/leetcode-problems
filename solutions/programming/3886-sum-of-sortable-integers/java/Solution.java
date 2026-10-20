package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public static int sortableIntegers(int[] nums) {
    int length = nums.length;

    int[] sorted = nums.clone();
    Arrays.sort(sorted);

    int result = 0;

    for (int k = 1; k * k <= length; k++) {
      if (length % k != 0) {
        continue;
      }

      if (canSort(nums, sorted, k)) {
        result += k;
      }

      int other = length / k;

      if (other != k && canSort(nums, sorted, other)) {
        result += other;
      }
    }

    return result;
  }

  private static boolean canSort(int[] nums, int[] sorted, int k) {
    int[] prefix = new int[k];

    for (int start = 0; start < nums.length; start += k) {
      buildPrefix(sorted, start, k, prefix);

      int matched = 0;
      boolean found = false;

      for (int i = 0; i < 2 * k - 1; i++) {
        int value = nums[start + i % k];

        while (matched > 0 && value != sorted[start + matched]) {
          matched = prefix[matched - 1];
        }

        if (value == sorted[start + matched]) {
          matched++;
        }

        if (matched == k) {
          found = true;
          break;
        }
      }

      if (!found) {
        return false;
      }
    }

    return true;
  }

  private static void buildPrefix(int[] sorted, int start, int length, int[] prefix) {

    prefix[0] = 0;

    int matched = 0;

    for (int i = 1; i < length; i++) {
      while (matched > 0 && sorted[start + i] != sorted[start + matched]) {
        matched = prefix[matched - 1];
      }

      if (sorted[start + i] == sorted[start + matched]) {
        matched++;
      }

      prefix[i] = matched;
    }
  }

  static void main() {
    System.out.println(sortableIntegers(new int[] {3, 1, 2})); // 3
    System.out.println(sortableIntegers(new int[] {7, 6, 5})); // 0
    System.out.println(sortableIntegers(new int[] {5, 8})); // 3
    System.out.println(sortableIntegers(new int[] {1, 2, 3, 4})); // 7
    System.out.println(sortableIntegers(new int[] {2, 1, 4, 3})); // 0
    System.out.println(sortableIntegers(new int[] {2, 3, 1, 5, 6, 4})); // 9
  }
}
