package dev.vkh.solutions;

class Solution {

  public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
    long left = 1L;
    long right = calculateUpperBound(mountainHeight, workerTimes);

    while (left < right) {
      long middleSeconds = left + (right - left) / 2;

      if (canReduceMountainToZero(mountainHeight, workerTimes, middleSeconds)) {
        right = middleSeconds;
      } else {
        left = middleSeconds + 1;
      }
    }

    return left;
  }

  private boolean canReduceMountainToZero(
      int mountainHeight, int[] workerTimes, long availableSeconds) {
    long totalReducedHeight = 0L;

    for (int workerTime : workerTimes) {
      totalReducedHeight += calculateMaximumHeightReducedByWorker(workerTime, availableSeconds);

      if (totalReducedHeight >= mountainHeight) {
        return true;
      }
    }

    return false;
  }

  private long calculateMaximumHeightReducedByWorker(int workerTime, long availableSeconds) {
    long left = 0L;
    long right = 200_000L;

    while (left < right) {
      long middleHeight = left + (right - left + 1) / 2;

      if (canWorkerReduceHeight(workerTime, middleHeight, availableSeconds)) {
        left = middleHeight;
      } else {
        right = middleHeight - 1;
      }
    }

    return left;
  }

  private boolean canWorkerReduceHeight(int workerTime, long heightReduced, long availableSeconds) {
    long requiredSeconds = workerTime * heightReduced * (heightReduced + 1L) / 2L;
    return requiredSeconds <= availableSeconds;
  }

  private long calculateUpperBound(int mountainHeight, int[] workerTimes) {
    int fastestWorkerTime = Integer.MAX_VALUE;

    for (int workerTime : workerTimes) {
      fastestWorkerTime = Math.min(fastestWorkerTime, workerTime);
    }

    return (long) fastestWorkerTime * mountainHeight * (mountainHeight + 1L) / 2L;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.minNumberOfSeconds(4, new int[] {2, 1, 1}));
    System.out.println(solution.minNumberOfSeconds(10, new int[] {3, 2, 2, 4}));
  }
}
