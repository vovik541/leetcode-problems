package dev.vkh.solutions;

class Solution {

  public int maxPathScore(int[][] grid, int k) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;
    int negativeInfinity = -1_000_000_000;

    int[][][] maxScore = new int[rowCount][columnCount][k + 1];

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
        for (int cost = 0; cost <= k; cost++) {
          maxScore[rowIndex][columnIndex][cost] = negativeInfinity;
        }
      }
    }

    maxScore[0][0][0] = 0;

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
        if (rowIndex == 0 && columnIndex == 0) {
          continue;
        }

        int currentCellValue = grid[rowIndex][columnIndex];
        int currentCellCost = currentCellValue == 0 ? 0 : 1;

        for (int totalCost = currentCellCost; totalCost <= k; totalCost++) {
          int previousCost = totalCost - currentCellCost;
          int bestPreviousScore = negativeInfinity;

          if (rowIndex > 0) {
            bestPreviousScore =
                Math.max(bestPreviousScore, maxScore[rowIndex - 1][columnIndex][previousCost]);
          }

          if (columnIndex > 0) {
            bestPreviousScore =
                Math.max(bestPreviousScore, maxScore[rowIndex][columnIndex - 1][previousCost]);
          }

          if (bestPreviousScore != negativeInfinity) {
            maxScore[rowIndex][columnIndex][totalCost] = bestPreviousScore + currentCellValue;
          }
        }
      }
    }

    int bestScore = -1;

    for (int totalCost = 0; totalCost <= k; totalCost++) {
      bestScore = Math.max(bestScore, maxScore[rowCount - 1][columnCount - 1][totalCost]);
    }

    return bestScore;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] grid1 = {
      {0, 1},
      {2, 0}
    };
    System.out.println(solution.maxPathScore(grid1, 1)); // 2

    int[][] grid2 = {
      {0, 1},
      {1, 2}
    };
    System.out.println(solution.maxPathScore(grid2, 1)); // -1
  }
}
