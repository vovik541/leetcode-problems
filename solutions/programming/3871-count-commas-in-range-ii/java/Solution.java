package dev.vkh.solutions;

class Solution {

  public static long countCommas(long n) {
    long totalCommas = 0;
    long commaThreshold = 1_000;

    while (commaThreshold <= n) {
      totalCommas += n - commaThreshold + 1;

      if (commaThreshold > n / 1_000) {
        break;
      }

      commaThreshold *= 1_000;
    }

    return totalCommas;
  }

  static void main() {
    System.out.println(countCommas(1002)); // 3
    System.out.println(countCommas(998)); // 0
    System.out.println(countCommas(1000)); // 1
    System.out.println(countCommas(1_000_000)); // 999002
  }
}
