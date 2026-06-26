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
    int[] nums1 = {1, 2, 2, 3};
    int target1 = 2;
    System.out.println(countMajoritySubarrays(nums1, target1)); // Output: 5

    int[] nums2 = {1, 1, 1, 1};
    int target2 = 1;
    System.out.println(countMajoritySubarrays(nums2, target2)); // Output: 10
  }
}
