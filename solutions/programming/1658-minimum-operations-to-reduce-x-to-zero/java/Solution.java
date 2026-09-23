package dev.vkh.solutions;

class Solution {

  public static int minOperations(int[] nums, int x) {
    int totalSum = 0;

    for (int number : nums) {
      totalSum += number;
    }

    int targetSum = totalSum - x;

    if (targetSum < 0) {
      return -1;
    }

    int leftIndex = 0;
    int currentSum = 0;
    int longestSubarrayLength = -1;

    for (int rightIndex = 0; rightIndex < nums.length; rightIndex++) {
      currentSum += nums[rightIndex];

      while (currentSum > targetSum && leftIndex <= rightIndex) {
        currentSum -= nums[leftIndex++];
      }

      if (currentSum == targetSum) {
        longestSubarrayLength = Math.max(longestSubarrayLength, rightIndex - leftIndex + 1);
      }
    }

    if (longestSubarrayLength == -1) {
      return -1;
    }

    return nums.length - longestSubarrayLength;
  }

  static void main() {
    System.out.println(minOperations(new int[] {1, 1, 4, 2, 3}, 5)); // 2
    System.out.println(minOperations(new int[] {5, 6, 7, 8, 9}, 4)); // -1
    System.out.println(minOperations(new int[] {3, 2, 20, 1, 1, 3}, 10)); // 5
  }
}
