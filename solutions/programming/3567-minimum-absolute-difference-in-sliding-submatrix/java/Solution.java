package dev.vkh.solutions;

import java.util.Arrays;
import java.util.TreeSet;

class Solution {
  public int[][] minAbsDiff(int[][] grid, int k) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    int answerRowCount = rowCount - k + 1;
    int answerColumnCount = columnCount - k + 1;

    int[][] minimumDifferenceBySubmatrix = new int[answerRowCount][answerColumnCount];

    for (int startRow = 0; startRow < answerRowCount; startRow++) {
      for (int startColumn = 0; startColumn < answerColumnCount; startColumn++) {
        TreeSet<Integer> distinctSortedValues = new TreeSet<>();

        for (int row = startRow; row < startRow + k; row++) {
          for (int column = startColumn; column < startColumn + k; column++) {
            distinctSortedValues.add(grid[row][column]);
          }
        }

        if (distinctSortedValues.size() <= 1) {
          minimumDifferenceBySubmatrix[startRow][startColumn] = 0;
          continue;
        }

        int minimumAbsoluteDifference = Integer.MAX_VALUE;
        Integer previousValue = null;

        for (int currentValue : distinctSortedValues) {
          if (previousValue != null) {
            minimumAbsoluteDifference =
                Math.min(minimumAbsoluteDifference, currentValue - previousValue);
          }
          previousValue = currentValue;
        }

        minimumDifferenceBySubmatrix[startRow][startColumn] = minimumAbsoluteDifference;
      }
    }

    return minimumDifferenceBySubmatrix;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] grid = {
      {1, 8},
      {3, -2}
    };
    int k = 2;
    printMatrix(solution.minAbsDiff(grid, k)); // [[2]]
  }

  private static void printMatrix(int[][] matrix) {
    System.out.println(Arrays.deepToString(matrix));
  }
}
