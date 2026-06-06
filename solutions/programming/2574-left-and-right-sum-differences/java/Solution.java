package dev.vkh.solutions;

import java.util.Arrays;

class Solution {
  public int[] leftRightDifference(int[] nums) {
    int rightSumTotal = 0;

    for (int i = 0; i < nums.length; i++) {
      rightSumTotal += nums[i];
    }
    int[] answer = new int[nums.length];

    int leftSum = 0;
    int rightSum = rightSumTotal;
    for (int i = 0; i < nums.length; i++) {
      rightSum -= nums[i];
      answer[i] = Math.abs(leftSum - rightSum);
      leftSum += nums[i];
    }

    return answer;
  }

  static void main() {
    System.out.println(
        Arrays.toString(new Solution().leftRightDifference(new int[] {10, 4, 8, 3}))); //[15,1,11,22]
  }
}
