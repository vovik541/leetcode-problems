package dev.vkh.solutions;

public class Solution {

  public boolean canPartitionGrid(int[][] grid) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    long totalSum = 0L;
    long[] rowSums = new long[rowCount];
    long[] columnSums = new long[columnCount];

    for (int row = 0; row < rowCount; row++) {
      for (int column = 0; column < columnCount; column++) {
        int cellValue = grid[row][column];
        totalSum += cellValue;
        rowSums[row] += cellValue;
        columnSums[column] += cellValue;
      }
    }

    long topSectionSum = 0L;
    for (int row = 0; row < rowCount - 1; row++) {
      topSectionSum += rowSums[row];
      long bottomSectionSum = totalSum - topSectionSum;

      if (topSectionSum == bottomSectionSum) {
        return true;
      }
    }

    long leftSectionSum = 0L;
    for (int column = 0; column < columnCount - 1; column++) {
      leftSectionSum += columnSums[column];
      long rightSectionSum = totalSum - leftSectionSum;

      if (leftSectionSum == rightSectionSum) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

    int[][] grid1 = {
      {1, 4},
      {2, 3}
    };

    int[][] grid2 = {
      {1, 3},
      {2, 4}
    };

    System.out.println(solution.canPartitionGrid(grid1)); // true
    System.out.println(solution.canPartitionGrid(grid2)); // false
  }
}
