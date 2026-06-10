package dev.vkh.solutions;

import java.util.PriorityQueue;

class Solution {

  public long maxTotalValue(int[] nums, int k) {
    RangeQuery rangeQuery = new RangeQuery(nums);
    PriorityQueue<SubarrayState> maxHeap =
        new PriorityQueue<>((first, second) -> Long.compare(second.value, first.value));

    for (int leftIndex = 0; leftIndex < nums.length; leftIndex++) {
      int rightIndex = nums.length - 1;
      long subarrayValue = rangeQuery.getRangeValue(leftIndex, rightIndex);
      maxHeap.offer(new SubarrayState(leftIndex, rightIndex, subarrayValue));
    }

    long maximumTotalValue = 0L;

    for (int selectedCount = 0; selectedCount < k; selectedCount++) {
      SubarrayState currentState = maxHeap.poll();
      maximumTotalValue += currentState.value;

      if (currentState.rightIndex > currentState.leftIndex) {
        int nextRightIndex = currentState.rightIndex - 1;
        long nextValue = rangeQuery.getRangeValue(currentState.leftIndex, nextRightIndex);

        maxHeap.offer(new SubarrayState(currentState.leftIndex, nextRightIndex, nextValue));
      }
    }

    return maximumTotalValue;
  }

  private static class SubarrayState {
    private final int leftIndex;
    private final int rightIndex;
    private final long value;

    private SubarrayState(int leftIndex, int rightIndex, long value) {
      this.leftIndex = leftIndex;
      this.rightIndex = rightIndex;
      this.value = value;
    }
  }

  private static class RangeQuery {
    private final int[] logs;
    private final int[][] maxSparseTable;
    private final int[][] minSparseTable;

    private RangeQuery(int[] nums) {
      int arrayLength = nums.length;

      logs = new int[arrayLength + 1];
      for (int length = 2; length <= arrayLength; length++) {
        logs[length] = logs[length / 2] + 1;
      }

      int levelCount = logs[arrayLength] + 1;
      maxSparseTable = new int[levelCount][arrayLength];
      minSparseTable = new int[levelCount][arrayLength];

      for (int index = 0; index < arrayLength; index++) {
        maxSparseTable[0][index] = nums[index];
        minSparseTable[0][index] = nums[index];
      }

      for (int level = 1; level < levelCount; level++) {
        int segmentLength = 1 << level;
        int halfSegmentLength = segmentLength >> 1;

        for (int index = 0; index + segmentLength <= arrayLength; index++) {
          maxSparseTable[level][index] =
              Math.max(
                  maxSparseTable[level - 1][index],
                  maxSparseTable[level - 1][index + halfSegmentLength]);

          minSparseTable[level][index] =
              Math.min(
                  minSparseTable[level - 1][index],
                  minSparseTable[level - 1][index + halfSegmentLength]);
        }
      }
    }

    private long getRangeValue(int leftIndex, int rightIndex) {
      int rangeLength = rightIndex - leftIndex + 1;
      int level = logs[rangeLength];
      int segmentLength = 1 << level;

      int maximumValue =
          Math.max(
              maxSparseTable[level][leftIndex],
              maxSparseTable[level][rightIndex - segmentLength + 1]);

      int minimumValue =
          Math.min(
              minSparseTable[level][leftIndex],
              minSparseTable[level][rightIndex - segmentLength + 1]);

      return (long) maximumValue - minimumValue;
    }
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.maxTotalValue(new int[] {1, 3, 2}, 2)); // 4
  }
}
