package dev.vkh.solutions;

import java.util.HashSet;
import java.util.Set;

class Solution {

  public int longestCommonPrefix(int[] arr1, int[] arr2) {
    Set<Integer> prefixesFromFirstArray = new HashSet<>();

    for (int value : arr1) {
      while (value > 0) {
        prefixesFromFirstArray.add(value);
        value /= 10;
      }
    }

    int longestPrefixLength = 0;

    for (int value : arr2) {
      while (value > 0) {
        if (prefixesFromFirstArray.contains(value)) {
          longestPrefixLength = Math.max(longestPrefixLength, countDigits(value));
          break;
        }

        value /= 10;
      }
    }

    return longestPrefixLength;
  }

  private int countDigits(int value) {
    int digitCount = 0;

    while (value > 0) {
      digitCount++;
      value /= 10;
    }

    return digitCount;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.longestCommonPrefix(new int[] {1, 10, 100}, new int[] {1000})); // 3
  }
}
