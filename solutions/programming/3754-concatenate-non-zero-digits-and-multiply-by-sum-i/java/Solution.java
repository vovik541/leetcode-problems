package dev.vkh.solutions;

public class Solution {

  public static int sumAndMultiply(int n) {
    int x = 0;
    int digitSum = 0;

    String number = String.valueOf(n);

    for (char currentChar : number.toCharArray()) {
      if (currentChar == '0') {
        continue;
      }

      int digit = currentChar - '0';

      x = x * 10 + digit;
      digitSum += digit;
    }

    return x * digitSum;
  }

  static void main() {
    System.out.println(sumAndMultiply(10203004)); // Output: 12340
    System.out.println(sumAndMultiply(1000)); // Output: 1
    System.out.println(sumAndMultiply(0)); // Output: 0
  }
}
