package dev.vkh.solutions;

class Solution {

  public static int closedIsland(int[][] grid) {
    int rows = grid.length;
    int columns = grid[0].length;

    for (int row = 0; row < rows; row++) {
      dfs(grid, row, 0);
      dfs(grid, row, columns - 1);
    }

    for (int column = 0; column < columns; column++) {
      dfs(grid, 0, column);
      dfs(grid, rows - 1, column);
    }

    int closedIslands = 0;

    for (int row = 1; row < rows - 1; row++) {
      for (int column = 1; column < columns - 1; column++) {
        if (grid[row][column] == 0) {
          dfs(grid, row, column);
          closedIslands++;
        }
      }
    }

    return closedIslands;
  }

  private static void dfs(int[][] grid, int row, int column) {
    if (row < 0
        || row >= grid.length
        || column < 0
        || column >= grid[0].length
        || grid[row][column] == 1) {
      return;
    }

    grid[row][column] = 1;

    dfs(grid, row + 1, column);
    dfs(grid, row - 1, column);
    dfs(grid, row, column + 1);
    dfs(grid, row, column - 1);
  }

  static void main() {
    System.out.println(
        closedIsland(
            new int[][] {
              {1, 1, 1, 1, 1, 1, 1, 0},
              {1, 0, 0, 0, 0, 1, 1, 0},
              {1, 0, 1, 0, 1, 1, 1, 0},
              {1, 0, 0, 0, 0, 1, 0, 1},
              {1, 1, 1, 1, 1, 1, 1, 0}
            })); // 2
    System.out.println(
        closedIsland(
            new int[][] {
              {0, 0, 1, 0, 0},
              {0, 1, 0, 1, 0},
              {0, 1, 1, 1, 0}
            })); // 1
    System.out.println(
        closedIsland(
            new int[][] {
              {1, 1, 1, 1, 1, 1, 1},
              {1, 0, 0, 0, 0, 0, 1},
              {1, 0, 1, 1, 1, 0, 1},
              {1, 0, 1, 0, 1, 0, 1},
              {1, 0, 1, 1, 1, 0, 1},
              {1, 0, 0, 0, 0, 0, 1},
              {1, 1, 1, 1, 1, 1, 1}
            })); // 2
    System.out.println(
        closedIsland(
            new int[][] {
              {0, 0},
              {0, 0}
            })); // 0
  }
}
