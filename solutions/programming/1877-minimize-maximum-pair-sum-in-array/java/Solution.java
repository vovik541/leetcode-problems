package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {
  static void main() {
    System.out.println(new Solution().minPairSum(new int[] {3, 5, 2, 3})); // 7
    //    System.out.println(new Solution().minPairSum(new int[]{3, 5, 4, 2, 4, 6}));  // 8
  }

  public int minPairSum(int[] nums) {
    Arrays.sort(nums);

    int leftIndex = 0;
    int rightIndex = nums.length - 1;

    int maximumPairSum = 0;

    while (leftIndex < rightIndex) {
      int currentPairSum = nums[leftIndex] + nums[rightIndex];
      if (currentPairSum > maximumPairSum) {
        maximumPairSum = currentPairSum;
      }
      leftIndex++;
      rightIndex--;
    }

    return maximumPairSum;
  }
}
