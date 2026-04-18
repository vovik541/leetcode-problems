package dev.vkh.solutions;

class Solution {
  public int mirrorDistance(int n) {
    int reversedN = 0;
    int tracedN = n;

    while (tracedN > 0) {
      reversedN = reversedN * 10 + tracedN % 10;
      tracedN = tracedN / 10;
    }
    return Math.abs(n - reversedN);
  }

  static void main() {
    System.out.println(new Solution().mirrorDistance(25));
  }
}
