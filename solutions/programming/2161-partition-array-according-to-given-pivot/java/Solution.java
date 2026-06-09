package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int[] pivotArray(int[] nums, int pivot) {
    int[] rearrangedNumbers = new int[nums.length];
    int insertIndex = 0;

    for (int value : nums) {
      if (value < pivot) {
        rearrangedNumbers[insertIndex++] = value;
      }
    }

    for (int value : nums) {
      if (value == pivot) {
        rearrangedNumbers[insertIndex++] = value;
      }
    }

    for (int value : nums) {
      if (value > pivot) {
        rearrangedNumbers[insertIndex++] = value;
      }
    }

    return rearrangedNumbers;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(
        Arrays.toString(solution.pivotArray(new int[] {-3, 4, 3, 2}, 2))); // [-3, 2, 4, 3]
  }
}
