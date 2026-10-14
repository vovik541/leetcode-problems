package dev.vkh.solutions;

class Solution {

  public static String maxSumOfSquares(int num, int sum) {
    if ((long) num * 9 < sum) {
      return "";
    }

    StringBuilder result = new StringBuilder(num);

    for (int i = 0; i < num; i++) {
      int digit = Math.min(9, sum);

      result.append(digit);
      sum -= digit;
    }

    return result.toString();
  }

  static void main() {
    System.out.println(maxSumOfSquares(2, 3)); // 30
    System.out.println(maxSumOfSquares(2, 17)); // 98
    System.out.println(maxSumOfSquares(1, 10)); // ""
    System.out.println(maxSumOfSquares(3, 20)); // 992
    System.out.println(maxSumOfSquares(4, 9)); // 9000
  }
}
