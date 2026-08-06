package dev.vkh.solutions;

class Solution {
  public static int smallestNumber(int n, int t) {
    int lastDigit = n % 10;
    if (lastDigit == 0) return n;

    int otherDigitsProduct = 1;
    int leftDigits = n / 10;

    while (leftDigits % 10 != 0) {
      otherDigitsProduct *= leftDigits % 10;
      leftDigits /= 10;
    }

    while (lastDigit % 10 != 0) {
      if (otherDigitsProduct * lastDigit % t == 0) return n / 10 * 10 + lastDigit;
      lastDigit++;
    }
    return (n / 10 + 1) * 10;
  }

  static void main() {
    System.out.println(smallestNumber(21, 2)); // 21
    System.out.println(smallestNumber(10, 2)); // 10
    System.out.println(smallestNumber(15, 3)); // 16
  }
}
