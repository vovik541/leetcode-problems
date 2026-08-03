package dev.vkh.solutions;

public class Solution {

  public static String stoneGameIII(int[] stoneValue) {
    int n = stoneValue.length;

    int[] dp = new int[n + 1];

    for (int index = n - 1; index >= 0; index--) {
      int currentSum = 0;
      dp[index] = Integer.MIN_VALUE;

      for (int take = 1; take <= 3 && index + take <= n; take++) {
        currentSum += stoneValue[index + take - 1];

        int nextIndex = index + take;
        int currentDifference = currentSum - dp[nextIndex];

        dp[index] = Math.max(dp[index], currentDifference);
      }
    }

    if (dp[0] > 0) {
      return "Alice";
    }

    if (dp[0] < 0) {
      return "Bob";
    }

    return "Tie";
  }

  static void main() {
    System.out.println(stoneGameIII(new int[] {1, 2, 3, 7})); // Bob
    System.out.println(stoneGameIII(new int[] {1, 2, 3, -9})); // Alice
    System.out.println(stoneGameIII(new int[] {1, 2, 3, 6})); // Tie
    System.out.println(stoneGameIII(new int[] {1, 2, 3, -1, -2, -3, 7})); // Alice
  }
}
