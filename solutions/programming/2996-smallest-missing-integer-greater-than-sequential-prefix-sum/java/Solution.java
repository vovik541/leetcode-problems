package dev.vkh.solutions;

import java.util.HashSet;
import java.util.Set;

class Solution {
  public static int missingInteger(int[] nums) {
    int prefixSum = nums[0];

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1] + 1) {
        prefixSum += nums[i];
      } else {
        break;
      }
    }

    Set<Integer> numbers = new HashSet<>();

    for (int num : nums) {
      numbers.add(num);
    }

    int answer = prefixSum;

    while (numbers.contains(answer)) {
      answer++;
    }

    return answer;
  }

  static void main() {
    System.out.println(missingInteger(new int[] {29, 30, 31, 32, 33, 34, 35, 36, 37})); // 297
    System.out.println(missingInteger(new int[] {1, 2, 3, 2, 5})); // 6
    System.out.println(missingInteger(new int[] {3, 4, 5, 1, 12, 14, 13})); // 15
  }
}
