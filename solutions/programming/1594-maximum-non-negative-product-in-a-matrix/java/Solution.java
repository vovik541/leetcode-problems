package dev.vkh.solutions;

public class Solution {

  private static final int MODULO = 1_000_000_007;

  public int maxProductPath(int[][] grid) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    long[][] maximumProduct = new long[rowCount][columnCount];
    long[][] minimumProduct = new long[rowCount][columnCount];

    maximumProduct[0][0] = grid[0][0];
    minimumProduct[0][0] = grid[0][0];

    // First row
    for (int column = 1; column < columnCount; column++) {
      long currentValue = grid[0][column];
      maximumProduct[0][column] = maximumProduct[0][column - 1] * currentValue;
      minimumProduct[0][column] = minimumProduct[0][column - 1] * currentValue;
    }

    // First column
    for (int row = 1; row < rowCount; row++) {
      long currentValue = grid[row][0];
      maximumProduct[row][0] = maximumProduct[row - 1][0] * currentValue;
      minimumProduct[row][0] = minimumProduct[row - 1][0] * currentValue;
    }

    for (int row = 1; row < rowCount; row++) {
      for (int column = 1; column < columnCount; column++) {
        long currentValue = grid[row][column];

        long candidateFromTopMax = maximumProduct[row - 1][column] * currentValue;
        long candidateFromTopMin = minimumProduct[row - 1][column] * currentValue;
        long candidateFromLeftMax = maximumProduct[row][column - 1] * currentValue;
        long candidateFromLeftMin = minimumProduct[row][column - 1] * currentValue;

        long currentMaximum =
            Math.max(
                Math.max(candidateFromTopMax, candidateFromTopMin),
                Math.max(candidateFromLeftMax, candidateFromLeftMin));

        long currentMinimum =
            Math.min(
                Math.min(candidateFromTopMax, candidateFromTopMin),
                Math.min(candidateFromLeftMax, candidateFromLeftMin));

        maximumProduct[row][column] = currentMaximum;
        minimumProduct[row][column] = currentMinimum;
      }
    }

    long answer = maximumProduct[rowCount - 1][columnCount - 1];

    if (answer < 0) {
      return -1;
    }

    return (int) (answer % MODULO);
  }

  static void main() {
    Solution solution = new Solution();

    int[][] grid = {
      {-1, -2, -3},
      {-2, -3, -3},
      {-3, -3, -2}
    };
    System.out.println(solution.maxProductPath(grid)); // -1
  }
}
