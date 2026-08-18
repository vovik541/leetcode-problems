package dev.vkh.solutions;

import java.util.HashMap;
import java.util.Map;

class Solution {
  public static int largestInteger(int[] nums, int k) {
    int n = nums.length;
    Map<Integer, Integer> frequency = new HashMap<>();

    for (int num : nums) {
      frequency.put(num, frequency.getOrDefault(num, 0) + 1);
    }

    if (k == 1) {
      int answer = -1;

      for (int num : nums) {
        if (frequency.get(num) == 1) {
          answer = Math.max(answer, num);
        }
      }

      return answer;
    }

    if (k == n) {
      int answer = -1;

      for (int num : nums) {
        answer = Math.max(answer, num);
      }

      return answer;
    }

    int answer = -1;

    int firstNumber = nums[0];
    int lastNumber = nums[n - 1];

    if (frequency.get(firstNumber) == 1) {
      answer = Math.max(answer, firstNumber);
    }

    if (frequency.get(lastNumber) == 1) {
      answer = Math.max(answer, lastNumber);
    }

    return answer;
  }

  static void main() {
    System.out.println(largestInteger(new int[] {3, 9, 2, 1, 7}, 3)); // 7
    System.out.println(largestInteger(new int[] {3, 9, 7, 2, 1, 7}, 4)); // 3
    System.out.println(largestInteger(new int[] {0, 0}, 1)); // -1
  }
}
