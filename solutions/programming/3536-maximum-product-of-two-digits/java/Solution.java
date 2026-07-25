package dev.vkh.solutions;

class Solution {
  public static int maxProduct(int n) {
    int[] digits = new int[10];
    int maxDigit = 0;

    int digit;
    while (n > 0) {
      digit = n % 10;
      n /= 10;

      digits[digit]++;

      if (digit > maxDigit) {
        maxDigit = digit;
      }
    }

    if (digits[maxDigit] > 1) {
      return maxDigit * maxDigit;
    }

    for (int i = maxDigit - 1; i > 0; i--) {
      if (digits[i] > 0) {
        return i * maxDigit;
      }
    }

    return 0;
  }

  static void main() {
    System.out.println(maxProduct(31)); // 3
    System.out.println(maxProduct(22)); // 4
    System.out.println(maxProduct(124)); // 8
  }
}
