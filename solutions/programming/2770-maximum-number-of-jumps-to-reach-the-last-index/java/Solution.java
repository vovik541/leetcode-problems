package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int maximumJumps(int[] nums, int target) {
    int arrayLength = nums.length;

    int[] maximumJumpsToIndex = new int[arrayLength];
    Arrays.fill(maximumJumpsToIndex, -1);

    maximumJumpsToIndex[0] = 0;

    for (int currentIndex = 1; currentIndex < arrayLength; currentIndex++) {
      for (int previousIndex = 0; previousIndex < currentIndex; previousIndex++) {
        if (maximumJumpsToIndex[previousIndex] == -1) {
          continue;
        }

        long difference = (long) nums[currentIndex] - nums[previousIndex];

        if (difference >= -target && difference <= target) {
          maximumJumpsToIndex[currentIndex] =
              Math.max(maximumJumpsToIndex[currentIndex], maximumJumpsToIndex[previousIndex] + 1);
        }
      }
    }

    return maximumJumpsToIndex[arrayLength - 1];
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.maximumJumps(new int[] {1, 3, 6, 4, 1, 2}, 2)); // 3
    System.out.println(solution.maximumJumps(new int[] {1, 3, 6, 4, 1, 2}, 3)); // 5
    System.out.println(solution.maximumJumps(new int[] {1, 3, 6, 4, 1, 2}, 0)); // -1
  }
}
