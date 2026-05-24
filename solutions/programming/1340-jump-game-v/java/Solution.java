package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int maxJumps(int[] arr, int d) {
    int arrayLength = arr.length;
    int[] maxVisitedFromIndex = new int[arrayLength];
    Arrays.fill(maxVisitedFromIndex, -1);

    int maximumVisitedIndices = 1;

    for (int index = 0; index < arrayLength; index++) {
      maximumVisitedIndices =
          Math.max(
              maximumVisitedIndices,
              calculateMaxVisitedFromIndex(arr, d, index, maxVisitedFromIndex));
    }

    return maximumVisitedIndices;
  }

  private int calculateMaxVisitedFromIndex(
      int[] arr, int maxJumpDistance, int currentIndex, int[] maxVisitedFromIndex) {
    if (maxVisitedFromIndex[currentIndex] != -1) {
      return maxVisitedFromIndex[currentIndex];
    }

    int bestVisitedCount = 1;

    for (int nextIndex = currentIndex + 1;
        nextIndex < arr.length && nextIndex <= currentIndex + maxJumpDistance;
        nextIndex++) {

      if (arr[nextIndex] >= arr[currentIndex]) {
        break;
      }

      bestVisitedCount =
          Math.max(
              bestVisitedCount,
              1
                  + calculateMaxVisitedFromIndex(
                      arr, maxJumpDistance, nextIndex, maxVisitedFromIndex));
    }

    for (int nextIndex = currentIndex - 1;
        nextIndex >= 0 && nextIndex >= currentIndex - maxJumpDistance;
        nextIndex--) {

      if (arr[nextIndex] >= arr[currentIndex]) {
        break;
      }

      bestVisitedCount =
          Math.max(
              bestVisitedCount,
              1
                  + calculateMaxVisitedFromIndex(
                      arr, maxJumpDistance, nextIndex, maxVisitedFromIndex));
    }

    maxVisitedFromIndex[currentIndex] = bestVisitedCount;
    return bestVisitedCount;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.maxJumps(new int[] {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12}, 2)); // 4
  }
}
