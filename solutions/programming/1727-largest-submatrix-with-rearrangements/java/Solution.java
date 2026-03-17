package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int largestSubmatrix(int[][] matrix) {
    int rowCount = matrix.length;
    int columnCount = matrix[0].length;

    int[][] consecutiveHeights = new int[rowCount][columnCount];

    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
      consecutiveHeights[0][columnIndex] = matrix[0][columnIndex];

      for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
        if (matrix[rowIndex][columnIndex] == 0) {
          consecutiveHeights[rowIndex][columnIndex] = 0;
        } else {
          consecutiveHeights[rowIndex][columnIndex] =
              consecutiveHeights[rowIndex - 1][columnIndex] + 1;
        }
      }
    }

    int maximumSubmatrixArea = 0;

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      int[] sortedHeightsForCurrentRow = Arrays.copyOf(consecutiveHeights[rowIndex], columnCount);
      Arrays.sort(sortedHeightsForCurrentRow);

      for (int columnIndex = columnCount - 1; columnIndex >= 0; columnIndex--) {
        int currentHeight = sortedHeightsForCurrentRow[columnIndex];
        int currentWidth = columnCount - columnIndex;
        int currentArea = currentHeight * currentWidth;

        maximumSubmatrixArea = Math.max(maximumSubmatrixArea, currentArea);
      }
    }

    return maximumSubmatrixArea;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] matrix = {
      {0, 0, 1},
      {1, 1, 1},
      {1, 0, 1}
    };
    System.out.println(solution.largestSubmatrix(matrix)); // 4

    matrix = new int[][] {{1, 0, 1, 0, 1}};
    System.out.println(solution.largestSubmatrix(matrix)); // 3
  }
}
