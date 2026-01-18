package dev.vkh.solutions;

class Solution {
  public int largestMagicSquare(int[][] grid) {
    int rowCount = grid.length;
    int colCount = grid[0].length;

    long[][] rowPrefix = new long[rowCount][colCount + 1];
    long[][] colPrefix = new long[rowCount + 1][colCount];

    long[][] mainDiagPrefix = new long[rowCount + 1][colCount + 1];

    long[][] antiDiagPrefix = new long[rowCount + 1][colCount + 1];

    for (int r = 0; r < rowCount; r++) {
      for (int c = 0; c < colCount; c++) {
        rowPrefix[r][c + 1] = rowPrefix[r][c] + grid[r][c];
        colPrefix[r + 1][c] = colPrefix[r][c] + grid[r][c];

        mainDiagPrefix[r + 1][c + 1] = mainDiagPrefix[r][c] + grid[r][c];
        antiDiagPrefix[r + 1][c] = antiDiagPrefix[r][c + 1] + grid[r][c];
      }
    }

    int maxPossibleSize = Math.min(rowCount, colCount);

    for (int squareSize = maxPossibleSize; squareSize >= 2; squareSize--) {
      for (int topRow = 0; topRow + squareSize <= rowCount; topRow++) {
        for (int leftCol = 0; leftCol + squareSize <= colCount; leftCol++) {
          if (isMagicSquare(
              grid,
              rowPrefix,
              colPrefix,
              mainDiagPrefix,
              antiDiagPrefix,
              topRow,
              leftCol,
              squareSize)) {
            return squareSize;
          }
        }
      }
    }

    return 1;
  }

  private boolean isMagicSquare(
      int[][] grid,
      long[][] rowPrefix,
      long[][] colPrefix,
      long[][] mainDiagPrefix,
      long[][] antiDiagPrefix,
      int topRow,
      int leftCol,
      int squareSize) {
    int bottomRow = topRow + squareSize - 1;
    int rightCol = leftCol + squareSize - 1;

    long targetSum = getMainDiagonalSum(mainDiagPrefix, topRow, leftCol, squareSize);
    long antiDiagonalSum = getAntiDiagonalSum(antiDiagPrefix, topRow, leftCol, squareSize);

    if (antiDiagonalSum != targetSum) return false;

    for (int r = topRow; r <= bottomRow; r++) {
      long rowSum = rowPrefix[r][rightCol + 1] - rowPrefix[r][leftCol];
      if (rowSum != targetSum) return false;
    }

    for (int c = leftCol; c <= rightCol; c++) {
      long colSum = colPrefix[bottomRow + 1][c] - colPrefix[topRow][c];
      if (colSum != targetSum) return false;
    }

    return true;
  }

  private long getMainDiagonalSum(
      long[][] mainDiagPrefix, int topRow, int leftCol, int squareSize) {
    int bottomRowExclusive = topRow + squareSize;
    int rightColExclusive = leftCol + squareSize;
    return mainDiagPrefix[bottomRowExclusive][rightColExclusive] - mainDiagPrefix[topRow][leftCol];
  }

  private long getAntiDiagonalSum(
      long[][] antiDiagPrefix, int topRow, int leftCol, int squareSize) {
    int bottomRowExclusive = topRow + squareSize;
    int rightCol = leftCol + squareSize - 1;
    return antiDiagPrefix[bottomRowExclusive][leftCol] - antiDiagPrefix[topRow][rightCol + 1];
  }

  static void main(String[] args) {
    Solution solution = new Solution();

    int[][] grid1 = {
      {7, 1, 4, 5, 6},
      {2, 5, 1, 6, 4},
      {1, 5, 4, 3, 2},
      {1, 2, 7, 3, 4}
    };

    int[][] grid2 = {
      {5, 1, 3, 1},
      {9, 3, 3, 1},
      {1, 3, 3, 8}
    };

    System.out.println("Expected: 3, Actual: " + solution.largestMagicSquare(grid1));
    System.out.println("Expected: 2, Actual: " + solution.largestMagicSquare(grid2));
  }
}
