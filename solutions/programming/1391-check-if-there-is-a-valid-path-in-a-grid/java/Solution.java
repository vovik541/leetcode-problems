package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

  private static final int up = 0;
  private static final int right = 1;
  private static final int down = 2;
  private static final int left = 3;

  private static final int[][] directions = {
    {-1, 0}, // up
    {0, 1}, // right
    {1, 0}, // down
    {0, -1} // left
  };

  private static final boolean[][] streetConnections = {
    {},
    {false, true, false, true}, // type 1: left, right
    {true, false, true, false}, // type 2: up, down
    {false, false, true, true}, // type 3: left, down
    {false, true, true, false}, // type 4: right, down
    {true, false, false, true}, // type 5: left, up
    {true, true, false, false} // type 6: right, up
  };

  public boolean hasValidPath(int[][] grid) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    boolean[][] visited = new boolean[rowCount][columnCount];
    Queue<int[]> cellsToVisit = new ArrayDeque<>();

    visited[0][0] = true;
    cellsToVisit.offer(new int[] {0, 0});

    while (!cellsToVisit.isEmpty()) {
      int[] currentCell = cellsToVisit.poll();
      int currentRow = currentCell[0];
      int currentColumn = currentCell[1];

      if (currentRow == rowCount - 1 && currentColumn == columnCount - 1) {
        return true;
      }

      int currentStreetType = grid[currentRow][currentColumn];

      for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
        if (!streetConnections[currentStreetType][directionIndex]) {
          continue;
        }

        int nextRow = currentRow + directions[directionIndex][0];
        int nextColumn = currentColumn + directions[directionIndex][1];

        if (!isInsideGrid(nextRow, nextColumn, rowCount, columnCount)
            || visited[nextRow][nextColumn]) {
          continue;
        }

        int oppositeDirectionIndex = (directionIndex + 2) % 4;
        int nextStreetType = grid[nextRow][nextColumn];

        if (!streetConnections[nextStreetType][oppositeDirectionIndex]) {
          continue;
        }

        visited[nextRow][nextColumn] = true;
        cellsToVisit.offer(new int[] {nextRow, nextColumn});
      }
    }

    return false;
  }

  private boolean isInsideGrid(int row, int column, int rowCount, int columnCount) {
    return row >= 0 && row < rowCount && column >= 0 && column < columnCount;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] grid1 = {
      {2, 4, 3},
      {6, 5, 2}
    };
    System.out.println(solution.hasValidPath(grid1)); // true

    int[][] grid2 = {
      {1, 2, 1},
      {1, 2, 1}
    };
    System.out.println(solution.hasValidPath(grid2)); // false
  }
}
