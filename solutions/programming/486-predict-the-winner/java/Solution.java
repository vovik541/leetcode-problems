package dev.vkh.solutions;

public class Solution {

  public static boolean predictTheWinner(int[] nums) {
    int n = nums.length;

    int[][] dp = new int[n][n];

    for (int index = 0; index < n; index++) {
      dp[index][index] = nums[index];
    }

    for (int length = 2; length <= n; length++) {
      for (int left = 0; left + length - 1 < n; left++) {
        int right = left + length - 1;

        int takeLeft = nums[left] - dp[left + 1][right];
        int takeRight = nums[right] - dp[left][right - 1];

        dp[left][right] = Math.max(takeLeft, takeRight);
      }
    }

    return dp[0][n - 1] >= 0;
  }

  static void main() {
    System.out.println(predictTheWinner(new int[] {1, 5, 2})); // false
    System.out.println(predictTheWinner(new int[] {1, 5, 233, 7})); //  true
    System.out.println(predictTheWinner(new int[] {1, 1})); // true
  }
}
