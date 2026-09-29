package dev.vkh.solutions;

class Solution {

  public static boolean hasValidPath(char[][] grid) {
    int rows = grid.length;
    int columns = grid[0].length;

    if ((rows + columns) % 2 == 0) {
      return false;
    }

    boolean[][] dp = new boolean[columns][rows + columns];

    dp[0][0] = true;

    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        if (row == 0 && column == 0) {
          continue;
        }

        int pathLength = row + column;

        for (int balance = 0; balance <= pathLength; balance++) {
          boolean reachable = false;

          if (row > 0) {
            reachable |= dp[column][balance];
          }

          if (column > 0) {
            reachable |= dp[column - 1][balance];
          }

          if (!reachable) {
            continue;
          }

          int newBalance = grid[row][column] == '(' ? balance + 1 : balance - 1;

          if (newBalance >= 0) {
            dp[column][newBalance] = true;
          }
        }
      }
    }

    return dp[columns - 1][0];
  }

  static void main() {
    System.out.println(
        hasValidPath(
            new char[][] {
              {'(', '(', '('},
              {')', '(', ')'},
              {'(', '(', ')'},
              {'(', '(', ')'}
            })); // true
    System.out.println(
        hasValidPath(
            new char[][] {
              {')', ')'},
              {'(', '('}
            })); // false
  }
}
