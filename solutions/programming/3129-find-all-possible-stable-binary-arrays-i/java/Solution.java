package dev.vkh.solutions;

class Solution {

  private static final int MOD = 1_000_000_007;

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.numberOfStableArrays(1, 1, 2)); // 2
    System.out.println(solution.numberOfStableArrays(1, 2, 1)); // 1
    System.out.println(solution.numberOfStableArrays(3, 3, 2)); // 14
  }

  public int numberOfStableArrays(int zero, int one, int limit) {
    int[][] stableArraysEndingWithZero = new int[zero + 1][one + 1];
    int[][] stableArraysEndingWithOne = new int[zero + 1][one + 1];

    for (int zeroCount = 1; zeroCount <= Math.min(zero, limit); zeroCount++) {
      stableArraysEndingWithZero[zeroCount][0] = 1;
    }

    for (int oneCount = 1; oneCount <= Math.min(one, limit); oneCount++) {
      stableArraysEndingWithOne[0][oneCount] = 1;
    }

    for (int zeroCount = 0; zeroCount <= zero; zeroCount++) {
      for (int oneCount = 0; oneCount <= one; oneCount++) {
        if (zeroCount == 0 && oneCount == 0) {
          continue;
        }

        if (zeroCount > 0) {
          long waysToEndWithZero = stableArraysEndingWithZero[zeroCount - 1][oneCount];
          waysToEndWithZero += stableArraysEndingWithOne[zeroCount - 1][oneCount];

          if (zeroCount - limit - 1 >= 0) {
            waysToEndWithZero -= stableArraysEndingWithOne[zeroCount - limit - 1][oneCount];
          }

          stableArraysEndingWithZero[zeroCount][oneCount] = normalizeModulo(waysToEndWithZero);
        }

        if (oneCount > 0) {
          long waysToEndWithOne = stableArraysEndingWithZero[zeroCount][oneCount - 1];
          waysToEndWithOne += stableArraysEndingWithOne[zeroCount][oneCount - 1];

          if (oneCount - limit - 1 >= 0) {
            waysToEndWithOne -= stableArraysEndingWithZero[zeroCount][oneCount - limit - 1];
          }

          stableArraysEndingWithOne[zeroCount][oneCount] = normalizeModulo(waysToEndWithOne);
        }
      }
    }

    return (stableArraysEndingWithZero[zero][one] + stableArraysEndingWithOne[zero][one]) % MOD;
  }

  private int normalizeModulo(long value) {
    value %= MOD;
    if (value < 0) {
      value += MOD;
    }
    return (int) value;
  }

//  public int numberOfStableArrays(int zero, int one, int limit) {
//    final int mod = 1_000_000_007;
//
//    int[][] dp0 = new int[zero + 1][one + 1];
//    int[][] dp1 = new int[zero + 1][one + 1];
//
//    for (int i = 1; i <= Math.min(zero, limit); ++i) dp0[i][0] = 1;
//    for (int j = 1; j <= Math.min(one, limit); ++j) dp1[0][j] = 1;
//
//    for (int i = 1; i <= zero; ++i) {
//      for (int j = 1; j <= one; ++j) {
//        for (int k = 1; k <= Math.min(limit, i); ++k)
//          dp0[i][j] = (dp0[i][j] + dp1[i - k][j]) % mod;
//        for (int k = 1; k <= Math.min(limit, j); ++k)
//          dp1[i][j] = (dp1[i][j] + dp0[i][j - k]) % mod;
//      }
//    }
//
//    return (dp0[zero][one] + dp1[zero][one]) % mod;
//  }
}
