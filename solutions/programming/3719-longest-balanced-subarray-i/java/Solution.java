package dev.vkh.solutions;

import java.util.HashSet;
import java.util.Set;

public class Solution {
  public int longestBalanced(int[] nums) {
    int arrayLength = nums.length;
    int maximumLength = 0;

    for (int leftIndex = 0; leftIndex < arrayLength; leftIndex++) {
      Set<Integer> distinctEvenNumbers = new HashSet<>();
      Set<Integer> distinctOddNumbers = new HashSet<>();

      for (int rightIndex = leftIndex; rightIndex < arrayLength; rightIndex++) {
        int currentValue = nums[rightIndex];

        if (currentValue % 2 == 0) {
          distinctEvenNumbers.add(currentValue);
        } else {
          distinctOddNumbers.add(currentValue);
        }

        if (distinctEvenNumbers.size() == distinctOddNumbers.size()) {
          maximumLength = Math.max(maximumLength, rightIndex - leftIndex + 1);
        }
      }
    }

    return maximumLength;
  }

  static void main() {
    Solution solution = new Solution();
    System.out.println(solution.longestBalanced(new int[] {3, 2, 2, 5, 4}));
  }
}
