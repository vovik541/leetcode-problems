package dev.vkh.solutions;

class Solution {
  private static final int[][] directions = {
    {1, 0},
    {-1, 0},
    {0, 1},
    {0, -1}
  };

  public boolean containsCycle(char[][] grid) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;
    boolean[][] visited = new boolean[rowCount][columnCount];

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
        if (!visited[rowIndex][columnIndex]
            && hasCycle(
                grid, visited, rowIndex, columnIndex, -1, -1, grid[rowIndex][columnIndex])) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean hasCycle(
      char[][] grid,
      boolean[][] visited,
      int currentRow,
      int currentColumn,
      int parentRow,
      int parentColumn,
      char targetCharacter) {
    visited[currentRow][currentColumn] = true;

    for (int[] direction : directions) {
      int nextRow = currentRow + direction[0];
      int nextColumn = currentColumn + direction[1];

      if (!isInsideGrid(grid, nextRow, nextColumn)
          || grid[nextRow][nextColumn] != targetCharacter) {
        continue;
      }

      if (nextRow == parentRow && nextColumn == parentColumn) {
        continue;
      }

      if (visited[nextRow][nextColumn]) {
        return true;
      }

      if (hasCycle(
          grid, visited, nextRow, nextColumn, currentRow, currentColumn, targetCharacter)) {
        return true;
      }
    }

    return false;
  }

  private boolean isInsideGrid(char[][] grid, int row, int column) {
    return row >= 0 && row < grid.length && column >= 0 && column < grid[0].length;
  }

  static void main() {
    Solution solution = new Solution();

    char[][] grid1 = {
      {'a', 'a', 'a', 'a'},
      {'a', 'b', 'b', 'a'},
      {'a', 'b', 'b', 'a'},
      {'a', 'a', 'a', 'a'}
    };
    System.out.println(solution.containsCycle(grid1)); // true

    char[][] grid2 = {
      {'c', 'c', 'c', 'a'},
      {'c', 'd', 'c', 'c'},
      {'c', 'c', 'e', 'c'},
      {'f', 'c', 'c', 'c'}
    };
    System.out.println(solution.containsCycle(grid2)); // true
  }
}
