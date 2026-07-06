package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {

  public static int removeCoveredIntervals(int[][] intervals) {
    Arrays.sort(
        intervals,
        (first, second) -> {
          if (first[0] != second[0]) {
            return Integer.compare(first[0], second[0]);
          }

          return Integer.compare(second[1], first[1]);
        });

    int remainingIntervals = 0;
    int maxEnd = 0;

    for (int[] interval : intervals) {
      int currentEnd = interval[1];

      if (currentEnd > maxEnd) {
        remainingIntervals++;
        maxEnd = currentEnd;
      }
    }

    return remainingIntervals;
  }

  static void main() {
    int[][] intervals1 = {
      {1, 4},
      {3, 6},
      {2, 8}
    };
    System.out.println(removeCoveredIntervals(intervals1)); // Output: 2

    int[][] intervals2 = {
      {1, 4},
      {2, 3}
    };
    System.out.println(removeCoveredIntervals(intervals2)); // Output: 1
  }
}
