package dev.vkh.solutions;

import java.util.Arrays;
import java.util.List;

class Solution {

  static void main() {
    Solution solution = new Solution();

    System.out.println(Arrays.toString(solution.minBitwiseArray(List.of(2, 3, 5, 7))));
  }

  public int[] minBitwiseArray(List<Integer> nums) {
    int[] result = new int[nums.size()];

    for (int index = 0; index < nums.size(); index++) {
      int targetOrValue = nums.get(index);
      result[index] = findMinimumX(targetOrValue);
    }

    return result;
  }

  private int findMinimumX(int targetOrValue) {
    int bestCandidate = Integer.MAX_VALUE;

    for (int trailingOnesCount = 0; trailingOnesCount <= 30; trailingOnesCount++) {
      int powerOfTwo = 1 << trailingOnesCount;
      int candidateX = targetOrValue - powerOfTwo;

      if (candidateX < 0) continue;

      if (hasExactlyTrailingOnes(candidateX, trailingOnesCount)) {
        bestCandidate = Math.min(bestCandidate, candidateX);
      }
    }

    return bestCandidate == Integer.MAX_VALUE ? -1 : bestCandidate;
  }

  private boolean hasExactlyTrailingOnes(int value, int trailingOnesCount) {
    if (trailingOnesCount == 0) {
      return (value & 1) == 0;
    }

    int lowerBitsMask = (1 << trailingOnesCount) - 1;

    boolean lowerBitsAllOnes = (value & lowerBitsMask) == lowerBitsMask;
    boolean nextBitIsZero = (value & (1 << trailingOnesCount)) == 0;

    return lowerBitsAllOnes && nextBitIsZero;
  }
}
