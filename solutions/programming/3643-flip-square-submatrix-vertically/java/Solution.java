package dev.vkh.solutions;

import java.util.Arrays;

class Solution {
  public static int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
    int topRow = x;
    int bottomRow = x + k - 1;

    while (topRow < bottomRow) {
      for (int column = y; column < y + k; column++) {
        int temporaryValue = grid[topRow][column];
        grid[topRow][column] = grid[bottomRow][column];
        grid[bottomRow][column] = temporaryValue;
      }

      topRow++;
      bottomRow--;
    }

    return grid;
  }

  static void main() {
    int[][] grid1 = {
      {1, 2, 3, 4},
      {5, 6, 7, 8},
      {9, 10, 11, 12},
      {13, 14, 15, 16}
    };

    int[][] result1 = reverseSubmatrix(grid1, 1, 0, 3);
    for (int[] row : result1) {
      System.out.println(Arrays.toString(row));
    }

    System.out.println();

    int[][] grid2 = {
      {3, 4, 2, 3},
      {2, 3, 4, 2}
    };

    int[][] result2 = reverseSubmatrix(grid2, 0, 2, 2);
    for (int[] row : result2) {
      System.out.println(Arrays.toString(row));
    }
  }
}
