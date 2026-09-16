package dev.vkh.solutions;

class Solution {

  private static final int MODULO = 1_000_000_007;

  public static int numberOfSets(int n, int k) {
    long[][] dp = new long[n][k + 1];
    long[][] prefixSum = new long[n][k + 1];

    for (int point = 0; point < n; point++) {
      dp[point][0] = 1;
      prefixSum[point][0] = point + 1;
    }

    for (int segmentCount = 1; segmentCount <= k; segmentCount++) {
      for (int endPoint = 1; endPoint < n; endPoint++) {
        dp[endPoint][segmentCount] =
            (dp[endPoint - 1][segmentCount] + prefixSum[endPoint - 1][segmentCount - 1]) % MODULO;

        prefixSum[endPoint][segmentCount] =
            (prefixSum[endPoint - 1][segmentCount] + dp[endPoint][segmentCount]) % MODULO;
      }
    }

    return (int) dp[n - 1][k];
  }

  static void main() {
    System.out.println(numberOfSets(4, 2)); // 5
    System.out.println(numberOfSets(3, 1)); // 3
    System.out.println(numberOfSets(30, 7)); // 796297179
  }
}
