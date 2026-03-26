package dev.vkh.solutions;

public class Solution {

  private static final int MAX_CELL_VALUE = 100_000;

  public boolean canPartitionGrid(int[][] grid) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    long totalSum = 0L;
    for (int[] row : grid) {
      for (int cellValue : row) {
        totalSum += cellValue;
      }
    }

    if (canMakeHorizontalCut(grid, rowCount, columnCount, totalSum)) {
      return true;
    }

    return canMakeVerticalCut(grid, rowCount, columnCount, totalSum);
  }

  private boolean canMakeHorizontalCut(int[][] grid, int rowCount, int columnCount, long totalSum) {
    int[] topSectionValueFrequency = new int[MAX_CELL_VALUE + 1];
    int[] bottomSectionValueFrequency = new int[MAX_CELL_VALUE + 1];

    for (int[] row : grid) {
      for (int cellValue : row) {
        bottomSectionValueFrequency[cellValue]++;
      }
    }

    long topSectionSum = 0L;

    for (int cutRow = 0; cutRow < rowCount - 1; cutRow++) {
      for (int column = 0; column < columnCount; column++) {
        int cellValue = grid[cutRow][column];
        topSectionSum += cellValue;
        topSectionValueFrequency[cellValue]++;
        bottomSectionValueFrequency[cellValue]--;
      }

      long bottomSectionSum = totalSum - topSectionSum;

      if (topSectionSum == bottomSectionSum) {
        return true;
      }

      if (topSectionSum > bottomSectionSum) {
        long requiredDiscount = topSectionSum - bottomSectionSum;
        if (canRemoveCellAndStayConnected(
            grid, 0, cutRow, 0, columnCount - 1, requiredDiscount, topSectionValueFrequency)) {
          return true;
        }
      } else {
        long requiredDiscount = bottomSectionSum - topSectionSum;
        if (canRemoveCellAndStayConnected(
            grid,
            cutRow + 1,
            rowCount - 1,
            0,
            columnCount - 1,
            requiredDiscount,
            bottomSectionValueFrequency)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean canMakeVerticalCut(int[][] grid, int rowCount, int columnCount, long totalSum) {
    int[] leftSectionValueFrequency = new int[MAX_CELL_VALUE + 1];
    int[] rightSectionValueFrequency = new int[MAX_CELL_VALUE + 1];

    for (int[] row : grid) {
      for (int cellValue : row) {
        rightSectionValueFrequency[cellValue]++;
      }
    }

    long leftSectionSum = 0L;

    for (int cutColumn = 0; cutColumn < columnCount - 1; cutColumn++) {
      for (int row = 0; row < rowCount; row++) {
        int cellValue = grid[row][cutColumn];
        leftSectionSum += cellValue;
        leftSectionValueFrequency[cellValue]++;
        rightSectionValueFrequency[cellValue]--;
      }

      long rightSectionSum = totalSum - leftSectionSum;

      if (leftSectionSum == rightSectionSum) {
        return true;
      }

      if (leftSectionSum > rightSectionSum) {
        long requiredDiscount = leftSectionSum - rightSectionSum;
        if (canRemoveCellAndStayConnected(
            grid, 0, rowCount - 1, 0, cutColumn, requiredDiscount, leftSectionValueFrequency)) {
          return true;
        }
      } else {
        long requiredDiscount = rightSectionSum - leftSectionSum;
        if (canRemoveCellAndStayConnected(
            grid,
            0,
            rowCount - 1,
            cutColumn + 1,
            columnCount - 1,
            requiredDiscount,
            rightSectionValueFrequency)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean canRemoveCellAndStayConnected(
      int[][] grid,
      int startRow,
      int endRow,
      int startColumn,
      int endColumn,
      long requiredDiscount,
      int[] valueFrequency) {
    if (requiredDiscount <= 0 || requiredDiscount > MAX_CELL_VALUE) {
      return false;
    }

    int discountValue = (int) requiredDiscount;
    int rectangleRowCount = endRow - startRow + 1;
    int rectangleColumnCount = endColumn - startColumn + 1;

    if (rectangleRowCount > 1 && rectangleColumnCount > 1) {
      return valueFrequency[discountValue] > 0;
    }

    if (rectangleRowCount == 1) {
      return grid[startRow][startColumn] == discountValue
          || grid[startRow][endColumn] == discountValue;
    }

    return grid[startRow][startColumn] == discountValue
        || grid[endRow][startColumn] == discountValue;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] grid1 = {
      {1, 4},
      {2, 3}
    };
    int[][] grid2 = {
      {1, 2},
      {3, 4}
    };
    int[][] grid3 = {
      {1, 2, 4},
      {2, 3, 5}
    };

    System.out.println(solution.canPartitionGrid(grid1)); // true
    System.out.println(solution.canPartitionGrid(grid2)); // true
    System.out.println(solution.canPartitionGrid(grid3)); // false
  }
}
