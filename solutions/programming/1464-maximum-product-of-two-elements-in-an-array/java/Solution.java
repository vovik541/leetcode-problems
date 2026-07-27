package dev.vkh.solutions;

import java.util.Arrays;

class Solution {
  public static int maxProduct(int[] nums) {
    Arrays.sort(nums);

    return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
  }

  static void main() {
    System.out.println(maxProduct(new int[] {3, 4, 5, 2})); // 12
    System.out.println(maxProduct(new int[] {1, 5, 4, 5})); // 16
    System.out.println(maxProduct(new int[] {3, 7})); // 12
  }
}
