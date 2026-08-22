package dev.vkh.solutions;

class Solution {
  public static boolean checkDivisibility(int n) {
    int nCopy = n;
    int sum = 0;
    int product = 1;

    while (nCopy != 0) {
      sum += nCopy % 10;
      product *= nCopy % 10;
      nCopy /= 10;
    }

    return n % (sum + product) == 0;
  }

  static void main() {
    System.out.println(checkDivisibility(99)); // true
    System.out.println(checkDivisibility(23)); // false
  }
}
