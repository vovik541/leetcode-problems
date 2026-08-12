package dev.vkh.solutions;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  public static int maxSubarrayLength(int[] nums, int k) {
    Map<Integer, Integer> frequency = new HashMap<>();

    int left = 0;
    int maxLength = 0;

    for (int right = 0; right < nums.length; right++) {
      int rightNumber = nums[right];

      frequency.put(rightNumber, frequency.getOrDefault(rightNumber, 0) + 1);

      while (frequency.get(rightNumber) > k) {
        int leftNumber = nums[left];

        frequency.put(leftNumber, frequency.get(leftNumber) - 1);
        left++;
      }

      int currentLength = right - left + 1;
      maxLength = Math.max(maxLength, currentLength);
    }

    return maxLength;
  }

  static void main() {
    System.out.println(maxSubarrayLength(new int[] {1, 2, 3, 1, 2, 3, 1, 2}, 2)); // 6
    System.out.println(maxSubarrayLength(new int[] {1, 2, 1, 2, 1, 2, 1, 2}, 1)); // 2
    System.out.println(maxSubarrayLength(new int[] {5, 5, 5, 5, 5, 5, 5}, 4)); // 4
  }
}
