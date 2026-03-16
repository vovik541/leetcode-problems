package dev.vkh.solutions;

import java.util.Arrays;
import java.util.TreeSet;

class Solution {

  static void main() {
    Solution solution = new Solution();

    int[][] grid = {
      {3, 4, 5, 1, 3},
      {3, 3, 4, 2, 3},
      {20, 30, 200, 40, 10},
      {1, 5, 5, 4, 1},
      {4, 3, 2, 2, 5}
    };
    System.out.println(Arrays.toString(solution.getBiggestThree(grid))); // [228, 216, 211]
  }

  public int[] getBiggestThree(int[][] grid) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    TreeSet<Integer> biggestDistinctSumsDescending =
        new TreeSet<>((firstValue, secondValue) -> secondValue - firstValue);

    for (int centerRow = 0; centerRow < rowCount; centerRow++) {
      for (int centerColumn = 0; centerColumn < columnCount; centerColumn++) {
        biggestDistinctSumsDescending.add(grid[centerRow][centerColumn]);
        trimToTopThree(biggestDistinctSumsDescending);

        int maximumRadius =
            Math.min(
                Math.min(centerRow, rowCount - 1 - centerRow),
                Math.min(centerColumn, columnCount - 1 - centerColumn));

        for (int radius = 1; radius <= maximumRadius; radius++) {
          int rhombusBorderSum = calculateRhombusBorderSum(grid, centerRow, centerColumn, radius);
          biggestDistinctSumsDescending.add(rhombusBorderSum);
          trimToTopThree(biggestDistinctSumsDescending);
        }
      }
    }

    int[] answer = new int[biggestDistinctSumsDescending.size()];
    int answerIndex = 0;
    for (int sum : biggestDistinctSumsDescending) {
      answer[answerIndex++] = sum;
    }

    return answer;
  }

  private int calculateRhombusBorderSum(int[][] grid, int centerRow, int centerColumn, int radius) {
    int currentRow = centerRow - radius;
    int currentColumn = centerColumn;
    int borderSum = grid[currentRow][currentColumn];

    for (int step = 1; step <= radius; step++) {
      currentRow++;
      currentColumn++;
      borderSum += grid[currentRow][currentColumn];
    }

    for (int step = 1; step <= radius; step++) {
      currentRow++;
      currentColumn--;
      borderSum += grid[currentRow][currentColumn];
    }

    for (int step = 1; step <= radius; step++) {
      currentRow--;
      currentColumn--;
      borderSum += grid[currentRow][currentColumn];
    }

    for (int step = 1; step < radius; step++) {
      currentRow--;
      currentColumn++;
      borderSum += grid[currentRow][currentColumn];
    }

    return borderSum;
  }

  private void trimToTopThree(TreeSet<Integer> biggestDistinctSumsDescending) {
    while (biggestDistinctSumsDescending.size() > 3) {
      biggestDistinctSumsDescending.pollLast();
    }
  }
}
