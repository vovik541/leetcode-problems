package dev.vkh.solutions;

import java.util.Arrays;
import java.util.List;

class Solution {

  public static int[] maximumWeight(List<List<Integer>> intervals) {
    int intervalCount = intervals.size();
    Interval[] sortedIntervals = new Interval[intervalCount];

    for (int index = 0; index < intervalCount; index++) {
      List<Integer> interval = intervals.get(index);

      sortedIntervals[index] =
          new Interval(interval.get(0), interval.get(1), interval.get(2), index);
    }

    Arrays.sort(
        sortedIntervals,
        (first, second) -> {
          if (first.right != second.right) {
            return Integer.compare(first.right, second.right);
          }

          return Integer.compare(first.left, second.left);
        });

    int[] previousNonOverlapping = new int[intervalCount];

    for (int index = 0; index < intervalCount; index++) {
      previousNonOverlapping[index] = findPreviousNonOverlapping(sortedIntervals, index);
    }

    State[][] dp = new State[5][intervalCount + 1];

    for (int selectedLimit = 0; selectedLimit <= 4; selectedLimit++) {
      dp[selectedLimit][0] = new State(0, new int[0]);
    }

    for (int index = 1; index <= intervalCount; index++) {
      Interval currentInterval = sortedIntervals[index - 1];

      dp[0][index] = new State(0, new int[0]);

      for (int selectedLimit = 1; selectedLimit <= 4; selectedLimit++) {
        State skipState = dp[selectedLimit][index - 1];

        int previousIndex = previousNonOverlapping[index - 1] + 1;
        State previousState = dp[selectedLimit - 1][previousIndex];

        int[] selectedIndices = appendAndSort(previousState.indices, currentInterval.originalIndex);

        State takeState = new State(previousState.score + currentInterval.weight, selectedIndices);

        dp[selectedLimit][index] = better(skipState, takeState);
      }
    }

    return dp[4][intervalCount].indices;
  }

  private static int findPreviousNonOverlapping(Interval[] intervals, int currentIndex) {

    int left = 0;
    int right = currentIndex - 1;
    int result = -1;

    while (left <= right) {
      int middle = left + (right - left) / 2;

      if (intervals[middle].right < intervals[currentIndex].left) {
        result = middle;
        left = middle + 1;
      } else {
        right = middle - 1;
      }
    }

    return result;
  }

  private static State better(State first, State second) {
    if (first.score != second.score) {
      return first.score > second.score ? first : second;
    }

    return isLexicographicallySmaller(first.indices, second.indices) ? first : second;
  }

  private static boolean isLexicographicallySmaller(int[] first, int[] second) {

    int commonLength = Math.min(first.length, second.length);

    for (int index = 0; index < commonLength; index++) {
      if (first[index] != second[index]) {
        return first[index] < second[index];
      }
    }

    return first.length < second.length;
  }

  private static int[] appendAndSort(int[] indices, int newIndex) {
    int[] result = Arrays.copyOf(indices, indices.length + 1);

    result[result.length - 1] = newIndex;
    Arrays.sort(result);

    return result;
  }

  private static class Interval {
    int left;
    int right;
    long weight;
    int originalIndex;

    Interval(int left, int right, int weight, int originalIndex) {
      this.left = left;
      this.right = right;
      this.weight = weight;
      this.originalIndex = originalIndex;
    }
  }

  private static class State {
    long score;
    int[] indices;

    State(long score, int[] indices) {
      this.score = score;
      this.indices = indices;
    }
  }
  static void main() {
    System.out.println(
            Arrays.toString(
                    maximumWeight(
                            List.of(
                                    List.of(1, 3, 2),
                                    List.of(4, 5, 2),
                                    List.of(1, 5, 5),
                                    List.of(6, 9, 3),
                                    List.of(6, 7, 1),
                                    List.of(8, 9, 1)))));
    // [2, 3]

    System.out.println(
            Arrays.toString(
                    maximumWeight(
                            List.of(
                                    List.of(5, 8, 1),
                                    List.of(6, 7, 7),
                                    List.of(4, 7, 3),
                                    List.of(9, 10, 6),
                                    List.of(7, 8, 2),
                                    List.of(11, 14, 3),
                                    List.of(3, 5, 5)))));
    // [1, 3, 5, 6]
  }
}
