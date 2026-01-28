package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {

  private static final long INF = Long.MAX_VALUE / 4;

  private static final class Cell {
    final int row;
    final int col;
    final int value;

    Cell(int row, int col, int value) {
      this.row = row;
      this.col = col;
      this.value = value;
    }
  }

  public int minCost(int[][] grid, int k) {
    int rowCount = grid.length;
    int colCount = grid[0].length;

    List<Cell> cellsSortedByValueDesc = new ArrayList<>(rowCount * colCount);
    for (int row = 0; row < rowCount; row++) {
      for (int col = 0; col < colCount; col++) {
        cellsSortedByValueDesc.add(new Cell(row, col, grid[row][col]));
      }
    }
    cellsSortedByValueDesc.sort(Comparator.comparingInt((Cell c) -> c.value).reversed());

    long[][] dpPrev = new long[rowCount][colCount];
    long[][] dpCur = new long[rowCount][colCount];
    long[][] teleportCost = new long[rowCount][colCount];

    fillWithInf(dpPrev);
    dpPrev[0][0] = 0;

    for (int row = 0; row < rowCount; row++) {
      for (int col = 0; col < colCount; col++) {
        if (row == 0 && col == 0) continue;

        long best = INF;
        int cellCost = grid[row][col];

        if (row > 0) best = Math.min(best, dpPrev[row - 1][col] + cellCost);
        if (col > 0) best = Math.min(best, dpPrev[row][col - 1] + cellCost);

        dpPrev[row][col] = best;
      }
    }

    for (int usedTeleports = 1; usedTeleports <= k; usedTeleports++) {
      fillWithInf(teleportCost);
      long minCostSoFar = INF;

      int index = 0;
      while (index < cellsSortedByValueDesc.size()) {
        int currentValue = cellsSortedByValueDesc.get(index).value;

        int groupStart = index;
        int groupEnd = groupStart;
        while (groupEnd < cellsSortedByValueDesc.size()
            && cellsSortedByValueDesc.get(groupEnd).value == currentValue) {
          groupEnd++;
        }

        for (int p = groupStart; p < groupEnd; p++) {
          Cell cell = cellsSortedByValueDesc.get(p);
          minCostSoFar = Math.min(minCostSoFar, dpPrev[cell.row][cell.col]);
        }

        for (int p = groupStart; p < groupEnd; p++) {
          Cell cell = cellsSortedByValueDesc.get(p);
          teleportCost[cell.row][cell.col] = minCostSoFar;
        }

        index = groupEnd;
      }

      fillWithInf(dpCur);
      dpCur[0][0] = 0;

      for (int row = 0; row < rowCount; row++) {
        for (int col = 0; col < colCount; col++) {
          if (row == 0 && col == 0) continue;

          long best = Math.min(dpPrev[row][col], teleportCost[row][col]);

          int cellCost = grid[row][col];
          if (row > 0) best = Math.min(best, dpCur[row - 1][col] + cellCost);
          if (col > 0) best = Math.min(best, dpCur[row][col - 1] + cellCost);

          dpCur[row][col] = best;
        }
      }

      long[][] temp = dpPrev;
      dpPrev = dpCur;
      dpCur = temp;
    }

    long answer = dpPrev[rowCount - 1][colCount - 1];
    return answer >= INF ? -1 : (int) answer;
  }

  private static void fillWithInf(long[][] matrix) {
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {
        matrix[row][col] = INF;
      }
    }
  }

  static void main() {
    int[][] grid = {
      {1, 3, 3},
      {2, 5, 4},
      {4, 3, 5}
    };
    //    int[][] grid = {
    //            {1, 2},
    //            {2, 3},
    //            {3, 4}
    //    };
    System.out.println(new Solution().minCost(grid, 2));
  }
}
