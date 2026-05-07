package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int[] maxValue(int[] nums) {
    int arrayLength = nums.length;

    int[] prefixMaximums = new int[arrayLength];
    int[] suffixMinimums = new int[arrayLength];

    prefixMaximums[0] = nums[0];
    for (int index = 1; index < arrayLength; index++) {
      prefixMaximums[index] = Math.max(prefixMaximums[index - 1], nums[index]);
    }

    suffixMinimums[arrayLength - 1] = nums[arrayLength - 1];
    for (int index = arrayLength - 2; index >= 0; index--) {
      suffixMinimums[index] = Math.min(suffixMinimums[index + 1], nums[index]);
    }

    int[] answer = new int[arrayLength];

    int segmentStartIndex = 0;
    int segmentMaximumValue = nums[0];

    for (int index = 0; index < arrayLength; index++) {
      segmentMaximumValue = Math.max(segmentMaximumValue, nums[index]);

      boolean isLastIndex = index == arrayLength - 1;
      boolean canCutHere = isLastIndex || prefixMaximums[index] <= suffixMinimums[index + 1];

      if (canCutHere) {
        for (int fillIndex = segmentStartIndex; fillIndex <= index; fillIndex++) {
          answer[fillIndex] = segmentMaximumValue;
        }

        if (!isLastIndex) {
          segmentStartIndex = index + 1;
          segmentMaximumValue = nums[segmentStartIndex];
        }
      }
    }

    return answer;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(Arrays.toString(solution.maxValue(new int[] {2, 1, 3})));
    // [2, 2, 3]

    System.out.println(Arrays.toString(solution.maxValue(new int[] {1, 2, 3, 4})));
    // [1, 2, 3, 4]
  }
}
