package dev.vkh.solutions;

public class Solution {

  public static boolean winnerSquareGame(int n) {
    boolean[] dp = new boolean[n + 1];

    for (int stones = 1; stones <= n; stones++) {
      for (int number = 1; number * number <= stones; number++) {
        int square = number * number;

        if (!dp[stones - square]) {
          dp[stones] = true;
          break;
        }
      }
    }

    return dp[n];
  }

  static void main() {
    System.out.println(winnerSquareGame(1)); // true
    System.out.println(winnerSquareGame(2)); // false
    System.out.println(winnerSquareGame(4)); // true
    System.out.println(winnerSquareGame(7)); // false
    System.out.println(winnerSquareGame(17)); // false
  }
}
