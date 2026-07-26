package dev.vkh.solutions;

import java.util.Arrays;

class Solution {
  public static int maximumProduct(int[] nums) {
    Arrays.sort(nums);

    int first = nums[nums.length - 1];
    int second = nums[nums.length - 2];
    int third = nums[nums.length - 3];

    if (first > 0 && second > 0 && third > 0) {
      if (nums[0] < 0 && nums[1] < 0) {
        return Math.max(first * second * third, first * nums[0] * nums[1]);
      }

      return first * second * third;
    }

    int biggerThanZero = 0;
    int smallerThanZero = 0;
    int zero = 0;
    for (int i = nums.length - 1; i >= 0; i--) {
      if (nums[i] > 0) {
        biggerThanZero++;
      } else if (nums[i] < 0) {
        smallerThanZero++;
      } else {
        zero++;
      }
    }

    if (biggerThanZero >= 1 && smallerThanZero >= 2) {
      return first * nums[0] * nums[1];
    }

    if (biggerThanZero == 0 && zero >= 1) {
      return 0;
    }

    return first * second * third;
  }

  static void main() {
    System.out.println(maximumProduct(new int[] {-100, -98, -1, 2, 3, 4})); // 39200
    System.out.println(maximumProduct(new int[] {1, 2, 3})); // 6
    System.out.println(maximumProduct(new int[] {1, 2, 3, 4})); // 24
    System.out.println(maximumProduct(new int[] {-1, -2, -3})); // -6
  }
}
