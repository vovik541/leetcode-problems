package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {
  static void main() {}

  public long minMergeCost(int[][] lists) {
    int listCount = lists.length;
    int totalMaskCount = 1 << listCount;

    int[][] mergedValuesByMask = new int[totalMaskCount][];
    int[] lengthByMask = new int[totalMaskCount];
    int[] medianByMask = new int[totalMaskCount];

    mergedValuesByMask[0] = new int[0];
    lengthByMask[0] = 0;

    for (int mask = 1; mask < totalMaskCount; mask++) {
      int lowestBit = mask & -mask;
      int listIndex = Integer.numberOfTrailingZeros(lowestBit);
      int maskWithoutLowestBit = mask ^ lowestBit;

      int[] previousMerged = mergedValuesByMask[maskWithoutLowestBit];
      int[] currentList = lists[listIndex];

      int[] merged = mergeSortedArrays(previousMerged, currentList);
      mergedValuesByMask[mask] = merged;

      int mergedLength = merged.length;
      lengthByMask[mask] = mergedLength;

      int medianIndex = (mergedLength - 1) / 2;
      medianByMask[mask] = merged[medianIndex];
    }

    long[] minCostByMask = new long[totalMaskCount];
    Arrays.fill(minCostByMask, Long.MAX_VALUE / 4);

    for (int i = 0; i < listCount; i++) {
      minCostByMask[1 << i] = 0;
    }

    for (int mask = 1; mask < totalMaskCount; mask++) {
      if ((mask & (mask - 1)) == 0) {
        continue;
      }

      for (int submask = (mask - 1) & mask; submask > 0; submask = (submask - 1) & mask) {
        int otherMask = mask ^ submask;
        if (otherMask == 0) continue;

        if (submask > otherMask) continue;

        long leftCost = minCostByMask[submask];
        long rightCost = minCostByMask[otherMask];
        if (leftCost == Long.MAX_VALUE / 4 || rightCost == Long.MAX_VALUE / 4) continue;

        long mergeCost =
                (long) lengthByMask[submask]
                        + (long) lengthByMask[otherMask]
                        + Math.abs((long) medianByMask[submask] - (long) medianByMask[otherMask]);

        long candidate = leftCost + rightCost + mergeCost;
        if (candidate < minCostByMask[mask]) {
          minCostByMask[mask] = candidate;
        }
      }
    }

    return minCostByMask[totalMaskCount - 1];
  }

  private int[] mergeSortedArrays(int[] firstSorted, int[] secondSorted) {
    int firstLength = firstSorted.length;
    int secondLength = secondSorted.length;

    int[] merged = new int[firstLength + secondLength];

    int firstIndex = 0;
    int secondIndex = 0;
    int mergedIndex = 0;

    while (firstIndex < firstLength && secondIndex < secondLength) {
      if (firstSorted[firstIndex] <= secondSorted[secondIndex]) {
        merged[mergedIndex++] = firstSorted[firstIndex++];
      } else {
        merged[mergedIndex++] = secondSorted[secondIndex++];
      }
    }

    while (firstIndex < firstLength) {
      merged[mergedIndex++] = firstSorted[firstIndex++];
    }
    while (secondIndex < secondLength) {
      merged[mergedIndex++] = secondSorted[secondIndex++];
    }

    return merged;
  }

  static void main(String[] args) {
    System.out.println(new Solution().minMergeCost(new int[][]{
            {1, 3, 5},
            {2, 4},
            {6, 7, 8}
    })); // expected 18

    System.out.println(new Solution().minMergeCost(new int[][]{
            {1, 1, 5},
            {1, 4, 7, 8}
    })); // expected 10

    System.out.println(new Solution().minMergeCost(new int[][]{
            {1},
            {3}
    })); // expected 4
  }

}
