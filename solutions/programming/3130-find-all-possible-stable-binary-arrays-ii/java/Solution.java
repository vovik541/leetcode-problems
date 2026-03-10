package dev.vkh.solutions;

class Solution {

  private static final int mod = 1_000_000_007;

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

        if (zeroCount > 0 && oneCount > 0) {
          long waysToEndWithZero =
              (long) stableArraysEndingWithZero[zeroCount - 1][oneCount]
                  + stableArraysEndingWithOne[zeroCount - 1][oneCount];

          if (zeroCount - limit - 1 >= 0) {
            waysToEndWithZero -= stableArraysEndingWithOne[zeroCount - limit - 1][oneCount];
          }

          stableArraysEndingWithZero[zeroCount][oneCount] = normalizeModulo(waysToEndWithZero);

          long waysToEndWithOne =
              (long) stableArraysEndingWithZero[zeroCount][oneCount - 1]
                  + stableArraysEndingWithOne[zeroCount][oneCount - 1];

          if (oneCount - limit - 1 >= 0) {
            waysToEndWithOne -= stableArraysEndingWithZero[zeroCount][oneCount - limit - 1];
          }

          stableArraysEndingWithOne[zeroCount][oneCount] = normalizeModulo(waysToEndWithOne);
        }
      }
    }

    return normalizeModulo(
        (long) stableArraysEndingWithZero[zero][one] + stableArraysEndingWithOne[zero][one]);
  }

  private int normalizeModulo(long value) {
    value %= mod;
    if (value < 0) {
      value += mod;
    }
    return (int) value;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.numberOfStableArrays(1, 1, 2)); // 2
    System.out.println(solution.numberOfStableArrays(1, 2, 1)); // 1
    System.out.println(solution.numberOfStableArrays(3, 3, 2)); // 14
  }
}
