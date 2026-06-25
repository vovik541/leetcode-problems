package dev.vkh.solutions;

public class Solution {

  public static int countMajoritySubarrays(int[] nums, int target) {
    int validSubarrayCount = 0;

    for (int start = 0; start < nums.length; start++) {
      int targetCount = 0;

      for (int end = start; end < nums.length; end++) {
        if (nums[end] == target) {
          targetCount++;
        }

        int currentLength = end - start + 1;

        if (2 * targetCount > currentLength) {
          validSubarrayCount++;
        }
      }
    }

    return validSubarrayCount;
  }

  static void main() {
    int[] nums = {1, 2, 2, 3};
    int target = 2;
    System.out.println(countMajoritySubarrays(nums, target)); // Output: 5
  }
}
