package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {
  public int minRemoval(int[] nums, int k) {
    Arrays.sort(nums);
    int i = 0;
    int maxLen = 0;

    for (int j = 0; j < nums.length; j++) {
      while ((long) nums[j] > (long) nums[i] * k) {
        i++;
      }
      maxLen = Math.max(maxLen, j - i + 1);
    }

    return nums.length - maxLen;
  }

  static void main() {
    System.out.println(new Solution().minRemoval(new int[] {2, 1, 5}, 2));
  }
}
