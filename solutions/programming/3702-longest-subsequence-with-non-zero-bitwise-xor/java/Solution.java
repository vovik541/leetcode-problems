package dev.vkh.solutions;

public class Solution {

  public static int longestSubsequence(int[] nums) {
    int totalXor = 0;
    boolean hasNonZero = false;

    for (int num : nums) {
      totalXor ^= num;

      if (num != 0) {
        hasNonZero = true;
      }
    }

    if (totalXor != 0) {
      return nums.length;
    }

    if (hasNonZero) {
      return nums.length - 1;
    }

    return 0;
  }

  static void main() {
    System.out.println(longestSubsequence(new int[] {1, 2, 3})); // 2
    System.out.println(longestSubsequence(new int[] {2, 3, 4})); // 3
    System.out.println(longestSubsequence(new int[] {0, 0, 0})); // 0
    System.out.println(longestSubsequence(new int[] {0, 1, 1})); // 2
  }
}
