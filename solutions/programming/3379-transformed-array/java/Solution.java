package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {
  public int[] constructTransformedArray(int[] nums) {
    int[] result = new int[nums.length];

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) {
        result[i] = nums[(i + nums[i]) % nums.length];
      } else {
        result[i] = nums[Math.abs(((nums[i] % nums.length + i) + nums.length) % nums.length)];
      }
    }

    return result;
  }

  static void main() {
    int[] result = new Solution().constructTransformedArray(new int[] {-1, 4, -1});

    Arrays.stream(result).forEach(System.out::println);
  }
}
