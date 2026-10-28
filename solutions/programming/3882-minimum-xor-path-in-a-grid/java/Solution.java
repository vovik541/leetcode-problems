package dev.vkh.solutions;

class Solution {

  public static int minCost(int[][] grid) {
    int rows = grid.length;
    int columns = grid[0].length;

    boolean[][][] dp = new boolean[rows][columns][1024];

    dp[0][0][grid[0][0]] = true;

    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        if (row == 0 && column == 0) {
          continue;
        }

        int value = grid[row][column];

        for (int xor = 0; xor < 1024; xor++) {
          if (row > 0 && dp[row - 1][column][xor]) {
            dp[row][column][xor ^ value] = true;
          }

          if (column > 0 && dp[row][column - 1][xor]) {
            dp[row][column][xor ^ value] = true;
          }
        }
      }
    }

    for (int xor = 0; xor < 1024; xor++) {
      if (dp[rows - 1][columns - 1][xor]) {
        return xor;
      }
    }

    return -1;
  }

  static void main() {
    System.out.println(
        minCost(
            new int[][] {
              {1, 2},
              {3, 4}
            })); // 6
    System.out.println(
        minCost(
            new int[][] {
              {6, 7},
              {5, 8}
            })); // 9
    System.out.println(minCost(new int[][] {{2, 7, 5}})); // 0
    System.out.println(minCost(new int[][] {{5}})); // 5
    System.out.println(
        minCost(
            new int[][] {
              {1, 2, 3},
              {4, 5, 6},
              {7, 8, 9}
            })); // 1
  }
}
