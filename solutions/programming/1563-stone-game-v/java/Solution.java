package dev.vkh.solutions;

public class Solution {

  public static int stoneGameV(int[] stoneValue) {
    int n = stoneValue.length;

    int[] prefixSum = new int[n + 1];

    for (int i = 0; i < n; i++) {
      prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
    }

    int[][] dp = new int[n][n];

    for (int length = 2; length <= n; length++) {
      for (int left = 0; left + length - 1 < n; left++) {
        int right = left + length - 1;

        for (int split = left; split < right; split++) {
          int leftSum = getRangeSum(prefixSum, left, split);
          int rightSum = getRangeSum(prefixSum, split + 1, right);

          if (leftSum < rightSum) {
            dp[left][right] = Math.max(dp[left][right], leftSum + dp[left][split]);
          } else if (leftSum > rightSum) {
            dp[left][right] = Math.max(dp[left][right], rightSum + dp[split + 1][right]);
          } else {
            dp[left][right] =
                Math.max(
                    dp[left][right], leftSum + Math.max(dp[left][split], dp[split + 1][right]));
          }
        }
      }
    }

    return dp[0][n - 1];
  }

  private static int getRangeSum(int[] prefixSum, int left, int right) {
    return prefixSum[right + 1] - prefixSum[left];
  }

  static void main() {
    System.out.println(stoneGameV(new int[] {6, 2, 3, 4, 5, 5})); // 18
    System.out.println(stoneGameV(new int[] {7, 7, 7, 7, 7, 7, 7})); // 28
    System.out.println(stoneGameV(new int[] {4})); // 0
  }
}
