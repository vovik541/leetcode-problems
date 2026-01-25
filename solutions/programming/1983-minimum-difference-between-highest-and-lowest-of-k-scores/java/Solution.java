package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  static void main() {
    Solution solution = new Solution();

    int[] nums1 = {90};
    int k = 1;
    System.out.println(solution.minimumDifference(nums1, k));
  }

  public int minimumDifference(int[] nums, int k) {
    if (k <= 1) {
      return 0;
    }

    Arrays.sort(nums);

    int minimumDifference = Integer.MAX_VALUE;

    for (int windowRightIndex = k - 1; windowRightIndex < nums.length; windowRightIndex++) {
      int windowLeftIndex = windowRightIndex - (k - 1);
      int currentDifference = nums[windowRightIndex] - nums[windowLeftIndex];
      minimumDifference = Math.min(minimumDifference, currentDifference);
    }

    return minimumDifference;
  }
}
