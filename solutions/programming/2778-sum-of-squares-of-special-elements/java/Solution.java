package dev.vkh.solutions;

class Solution {

  public static int sumOfSquares(int[] nums) {
    int length = nums.length;
    int sum = 0;

    for (int i = 1; i <= length; i++) {
      if (length % i == 0) {
        sum += nums[i - 1] * nums[i - 1];
      }
    }

    return sum;
  }

  static void main() {
    System.out.println(sumOfSquares(new int[] {1, 2, 3, 4})); // 21
    System.out.println(sumOfSquares(new int[] {2, 7, 1, 19, 18, 3})); // 63
    System.out.println(sumOfSquares(new int[] {5})); // 25
  }
}
