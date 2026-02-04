package dev.vkh.solutions;

public class Solution {

  public long maxSumTrionic(int[] nums) {
    long negativeInfinity = Long.MIN_VALUE / 4;

    long bestSingleElementSum = nums[0];
    long bestFirstIncreasingSum = negativeInfinity;
    long bestDecreasingSum = negativeInfinity;
    long bestLastIncreasingSum = negativeInfinity;

    long bestAnswer = negativeInfinity;

    for (int index = 1; index < nums.length; index++) {
      int previousValue = nums[index - 1];
      int currentValue = nums[index];

      long nextBestSingleElementSum = currentValue;

      long nextBestFirstIncreasingSum = bestFirstIncreasingSum;
      long nextBestDecreasingSum = bestDecreasingSum;
      long nextBestLastIncreasingSum = bestLastIncreasingSum;

      if (currentValue > previousValue) {
        nextBestFirstIncreasingSum =
            Math.max(
                bestFirstIncreasingSum == negativeInfinity
                    ? negativeInfinity
                    : bestFirstIncreasingSum + currentValue,
                bestSingleElementSum + currentValue);

        nextBestLastIncreasingSum =
            Math.max(
                bestLastIncreasingSum == negativeInfinity
                    ? negativeInfinity
                    : bestLastIncreasingSum + currentValue,
                bestDecreasingSum == negativeInfinity
                    ? negativeInfinity
                    : bestDecreasingSum + currentValue);
      } else if (currentValue < previousValue) {
        nextBestDecreasingSum =
            Math.max(
                bestDecreasingSum == negativeInfinity
                    ? negativeInfinity
                    : bestDecreasingSum + currentValue,
                bestFirstIncreasingSum == negativeInfinity
                    ? negativeInfinity
                    : bestFirstIncreasingSum + currentValue);
      }

      bestSingleElementSum = nextBestSingleElementSum;
      bestFirstIncreasingSum = nextBestFirstIncreasingSum;
      bestDecreasingSum = nextBestDecreasingSum;
      bestLastIncreasingSum = nextBestLastIncreasingSum;

      bestAnswer = Math.max(bestAnswer, bestLastIncreasingSum);
    }

    return bestAnswer;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.maxSumTrionic(new int[] {0, -2, -1, -3, 0, 2, -1}));
    System.out.println(solution.maxSumTrionic(new int[] {1, 4, 2, 7}));
  }
}
