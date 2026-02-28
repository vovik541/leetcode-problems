package dev.vkh.solutions;

class Solution {

  private static final int MOD = 1_000_000_007;

  public int concatenatedBinary(int n) {
    long result = 0L;
    int currentBitLength = 0;

    for (int value = 1; value <= n; value++) {
      if ((value & (value - 1)) == 0) {
        currentBitLength++;
      }

      result = ((result << currentBitLength) % MOD + value) % MOD;
    }

    return (int) result;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.concatenatedBinary(1)); // 1
    System.out.println(solution.concatenatedBinary(3)); // 27
    System.out.println(solution.concatenatedBinary(12)); // 505379714
  }
}
