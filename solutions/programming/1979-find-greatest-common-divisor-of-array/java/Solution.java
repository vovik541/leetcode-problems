package dev.vkh.solutions;

public class Solution {

  public static int findGCD(int[] nums) {
    int minNumber = nums[0];
    int maxNumber = nums[0];

    for (int num : nums) {
      minNumber = Math.min(minNumber, num);
      maxNumber = Math.max(maxNumber, num);
    }

    return gcd(minNumber, maxNumber);
  }

  private static int gcd(int firstNumber, int secondNumber) {
    while (secondNumber != 0) {
      int temp = firstNumber % secondNumber;
      firstNumber = secondNumber;
      secondNumber = temp;
    }

    return firstNumber;
  }

  static void main() {
    System.out.println(findGCD(new int[] {2, 5, 6, 9, 10})); // Output: 2
    System.out.println(findGCD(new int[] {7, 5, 6, 8, 3})); // Output: 1
    System.out.println(findGCD(new int[] {3, 3})); // Output: 3
  }
}
