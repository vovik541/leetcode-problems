package dev.vkh.solutions;

public class Solution {
  public static boolean hasAlternatingBits(int n) {
    int xorWithShiftedNumber = n ^ (n >> 1);

    return (xorWithShiftedNumber & (xorWithShiftedNumber + 1)) == 0;
  }

  static void main() {
    System.out.println(hasAlternatingBits(5));
    System.out.println(hasAlternatingBits(7));
    System.out.println(hasAlternatingBits(11));
  }
}
