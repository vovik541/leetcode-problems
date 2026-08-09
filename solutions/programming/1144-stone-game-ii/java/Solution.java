package dev.vkh.solutions;

import java.util.*;

public class Solution {

  public static int stoneGameII(int[] piles) {
    int n = piles.length;

    int[] suffixSum = new int[n + 1];

    for (int i = n - 1; i >= 0; i--) {
      suffixSum[i] = suffixSum[i + 1] + piles[i];
    }

    int[][] memo = new int[n][n + 1];

    for (int[] row : memo) {
      Arrays.fill(row, -1);
    }

    return dfs(0, 1, piles, suffixSum, memo);
  }

  private static int dfs(int index, int m, int[] piles, int[] suffixSum, int[][] memo) {

    int n = piles.length;

    if (index >= n) {
      return 0;
    }

    if (index + 2 * m >= n) {
      return suffixSum[index];
    }

    if (memo[index][m] != -1) {
      return memo[index][m];
    }

    int maxStones = 0;

    for (int x = 1; x <= 2 * m; x++) {
      int opponentStones = dfs(index + x, Math.max(m, x), piles, suffixSum, memo);

      int currentPlayerStones = suffixSum[index] - opponentStones;

      maxStones = Math.max(maxStones, currentPlayerStones);
    }

    memo[index][m] = maxStones;

    return maxStones;
  }

  static void main() {
    int[] piles1 = {2, 7, 9, 4, 4};
    System.out.println(stoneGameII(piles1)); // 10
    int[] piles2 = {1, 2, 3, 4, 5, 100};
    System.out.println(stoneGameII(piles2)); // 104
  }
}
