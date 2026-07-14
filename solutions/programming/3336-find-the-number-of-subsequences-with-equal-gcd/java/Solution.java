package dev.vkh.solutions;


public class Solution {

  private static final int MOD = 1_000_000_007;
  private static final int MAX_VALUE = 200;

  public int subsequencePairCount(int[] nums) {
    long[][] dp = new long[MAX_VALUE + 1][MAX_VALUE + 1];

    dp[0][0] = 1;

    for (int num : nums) {
      long[][] nextDp = new long[MAX_VALUE + 1][MAX_VALUE + 1];

      for (int gcd1 = 0; gcd1 <= MAX_VALUE; gcd1++) {
        for (int gcd2 = 0; gcd2 <= MAX_VALUE; gcd2++) {
          if (dp[gcd1][gcd2] == 0) {
            continue;
          }

          long currentWays = dp[gcd1][gcd2];

          nextDp[gcd1][gcd2] = (nextDp[gcd1][gcd2] + currentWays) % MOD;

          int newGcd1 = gcd(gcd1, num);
          nextDp[newGcd1][gcd2] = (nextDp[newGcd1][gcd2] + currentWays) % MOD;

          int newGcd2 = gcd(gcd2, num);
          nextDp[gcd1][newGcd2] = (nextDp[gcd1][newGcd2] + currentWays) % MOD;
        }
      }

      dp = nextDp;
    }

    long answer = 0;

    for (int gcd = 1; gcd <= MAX_VALUE; gcd++) {
      answer = (answer + dp[gcd][gcd]) % MOD;
    }

    return (int) answer;
  }

  private int gcd(int firstNumber, int secondNumber) {
    while (secondNumber != 0) {
      int temp = firstNumber % secondNumber;
      firstNumber = secondNumber;
      secondNumber = temp;
    }

    return firstNumber;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.subsequencePairCount(new int[]{1, 2, 3, 4})); // Output: 10
    System.out.println(solution.subsequencePairCount(new int[]{10, 20, 30})); // Output: 2
    System.out.println(solution.subsequencePairCount(new int[]{1, 1, 1, 1})); // Output: 50
  }
}
