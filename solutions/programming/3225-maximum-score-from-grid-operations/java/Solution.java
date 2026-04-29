package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  static void main() {
    Solution solution = new Solution();

    int[][] grid1 = {
      {0, 0, 0, 0, 0},
      {0, 0, 3, 0, 0},
      {0, 1, 0, 0, 0},
      {5, 0, 0, 3, 0},
      {0, 0, 0, 0, 2}
    };
    System.out.println(solution.maximumScore(grid1)); // 11

    int[][] grid2 = {
      {10, 9, 0, 0, 15},
      {7, 1, 0, 8, 0},
      {5, 20, 0, 11, 0},
      {0, 0, 0, 1, 2},
      {8, 12, 1, 10, 3}
    };
    System.out.println(solution.maximumScore(grid2)); // 94
  }

  public long maximumScore(int[][] grid) {
    int size = grid.length;

    if (size == 1) {
      return 0L;
    }

    long[][] columnPrefixSums = buildColumnPrefixSums(grid);
    int maxHeight = size;
    long negativeInfinity = Long.MIN_VALUE / 4;

    long[][] maxScoreByLastTwoHeights = new long[maxHeight + 1][maxHeight + 1];

    for (long[] row : maxScoreByLastTwoHeights) {
      Arrays.fill(row, negativeInfinity);
    }

    for (int firstColumnHeight = 0; firstColumnHeight <= maxHeight; firstColumnHeight++) {
      for (int secondColumnHeight = 0; secondColumnHeight <= maxHeight; secondColumnHeight++) {
        maxScoreByLastTwoHeights[firstColumnHeight][secondColumnHeight] =
            calculateColumnScore(columnPrefixSums, 0, 0, firstColumnHeight, secondColumnHeight);
      }
    }

    for (int columnIndex = 2; columnIndex < size; columnIndex++) {
      long[][] nextMaxScoreByLastTwoHeights = new long[maxHeight + 1][maxHeight + 1];

      for (long[] row : nextMaxScoreByLastTwoHeights) {
        Arrays.fill(row, negativeInfinity);
      }

      for (int beforePreviousHeight = 0;
          beforePreviousHeight <= maxHeight;
          beforePreviousHeight++) {
        for (int previousHeight = 0; previousHeight <= maxHeight; previousHeight++) {
          long currentScore = maxScoreByLastTwoHeights[beforePreviousHeight][previousHeight];

          if (currentScore == negativeInfinity) {
            continue;
          }

          for (int currentHeight = 0; currentHeight <= maxHeight; currentHeight++) {
            long addedScore =
                calculateColumnScore(
                    columnPrefixSums,
                    columnIndex - 1,
                    beforePreviousHeight,
                    previousHeight,
                    currentHeight);

            nextMaxScoreByLastTwoHeights[previousHeight][currentHeight] =
                Math.max(
                    nextMaxScoreByLastTwoHeights[previousHeight][currentHeight],
                    currentScore + addedScore);
          }
        }
      }

      maxScoreByLastTwoHeights = nextMaxScoreByLastTwoHeights;
    }

    long maximumScore = 0L;

    for (int beforeLastHeight = 0; beforeLastHeight <= maxHeight; beforeLastHeight++) {
      for (int lastHeight = 0; lastHeight <= maxHeight; lastHeight++) {
        long currentScore = maxScoreByLastTwoHeights[beforeLastHeight][lastHeight];

        if (currentScore == negativeInfinity) {
          continue;
        }

        long finalColumnScore =
            calculateColumnScore(columnPrefixSums, size - 1, beforeLastHeight, lastHeight, 0);

        maximumScore = Math.max(maximumScore, currentScore + finalColumnScore);
      }
    }

    return maximumScore;
  }

  private long[][] buildColumnPrefixSums(int[][] grid) {
    int size = grid.length;
    long[][] columnPrefixSums = new long[size][size + 1];

    for (int columnIndex = 0; columnIndex < size; columnIndex++) {
      for (int rowIndex = 0; rowIndex < size; rowIndex++) {
        columnPrefixSums[columnIndex][rowIndex + 1] =
            columnPrefixSums[columnIndex][rowIndex] + grid[rowIndex][columnIndex];
      }
    }

    return columnPrefixSums;
  }

  private long calculateColumnScore(
      long[][] columnPrefixSums,
      int columnIndex,
      int leftHeight,
      int currentHeight,
      int rightHeight) {
    int highestAdjacentBlackHeight = Math.max(leftHeight, rightHeight);

    if (highestAdjacentBlackHeight <= currentHeight) {
      return 0L;
    }

    return columnPrefixSums[columnIndex][highestAdjacentBlackHeight]
        - columnPrefixSums[columnIndex][currentHeight];
  }
}
