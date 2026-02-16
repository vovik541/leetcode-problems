package dev.vkh.solutions;

public class Solution {
  public int reverseBits(int n) {
    int reversedNumber = 0;

    for (int bitIndex = 0; bitIndex < 32; bitIndex++) {
      reversedNumber <<= 1;
      reversedNumber |= (n & 1);
      n >>>= 1;
    }

    return reversedNumber;
  }

  static void main() {
    Solution s = new Solution();
    System.out.println(s.reverseBits(43261596));
  }
}
