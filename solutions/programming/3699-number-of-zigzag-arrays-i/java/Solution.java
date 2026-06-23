package dev.vkh.solutions;

public class Solution {

  private static final int MOD = 1_000_000_007;

  public static int zigZagArrays(int n, int l, int r) {
    int valueCount = r - l + 1;

    long[] up = new long[valueCount + 1];
    long[] down = new long[valueCount + 1];

    for (int value = 1; value <= valueCount; value++) {
      up[value] = value - 1;
      down[value] = valueCount - value;
    }

    for (int length = 3; length <= n; length++) {
      long[] prefixDown = new long[valueCount + 1];
      long[] suffixUp = new long[valueCount + 2];

      for (int value = 1; value <= valueCount; value++) {
        prefixDown[value] = (prefixDown[value - 1] + down[value]) % MOD;
      }

      for (int value = valueCount; value >= 1; value--) {
        suffixUp[value] = (suffixUp[value + 1] + up[value]) % MOD;
      }

      long[] newUp = new long[valueCount + 1];
      long[] newDown = new long[valueCount + 1];

      for (int value = 1; value <= valueCount; value++) {
        newUp[value] = prefixDown[value - 1];
        newDown[value] = suffixUp[value + 1];
      }

      up = newUp;
      down = newDown;
    }

    long answer = 0;

    for (int value = 1; value <= valueCount; value++) {
      answer = (answer + up[value] + down[value]) % MOD;
    }

    return (int) answer;
  }

  static void main() {
    int n1 = 3;
    int l1 = 4;
    int r1 = 5;
    System.out.println(zigZagArrays(n1, l1, r1)); // Output: 2
  }
}
