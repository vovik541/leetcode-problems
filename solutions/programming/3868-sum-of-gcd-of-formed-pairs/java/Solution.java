package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {

  public static long gcdSum(int[] nums) {
    int n = nums.length;
    int[] prefixGcd = new int[n];

    int prefixMax = 0;

    for (int i = 0; i < n; i++) {
      prefixMax = Math.max(prefixMax, nums[i]);
      prefixGcd[i] = gcd(nums[i], prefixMax);
    }

    Arrays.sort(prefixGcd);

    int left = 0;
    int right = n - 1;
    long answer = 0;

    while (left < right) {
      answer += gcd(prefixGcd[left], prefixGcd[right]);
      left++;
      right--;
    }

    return answer;
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
    System.out.println(gcdSum(new int[] {2, 6, 4})); // Output: 2
    System.out.println(gcdSum(new int[] {3, 6, 2, 8})); // Output: 5
  }
}
