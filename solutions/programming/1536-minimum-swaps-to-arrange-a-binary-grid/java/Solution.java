package dev.vkh.solutions;

class Solution {

  static void main() {
    Solution solution = new Solution();

    int[][] grid1 = {
      {0, 0, 1},
      {1, 1, 0},
      {1, 0, 0}
    };
    System.out.println(solution.minSwaps(grid1)); // 3

    int[][] grid2 = {
      {0, 1, 1, 0},
      {0, 1, 1, 0},
      {0, 1, 1, 0},
      {0, 1, 1, 0}
    };
    System.out.println(solution.minSwaps(grid2)); // -1

    int[][] grid3 = {
      {1, 0, 0},
      {1, 1, 0},
      {1, 1, 1}
    };
    System.out.println(solution.minSwaps(grid3)); // 0
  }

  public int minSwaps(int[][] grid) {
    int n = grid.length;

    int[] trailingZerosPerRow = new int[n];
    for (int rowIndex = 0; rowIndex < n; rowIndex++) {
      int trailingZeros = 0;
      for (int colIndex = n - 1; colIndex >= 0; colIndex--) {
        if (grid[rowIndex][colIndex] == 0) {
          trailingZeros++;
        } else {
          break;
        }
      }
      trailingZerosPerRow[rowIndex] = trailingZeros;
    }

    int totalSwaps = 0;

    for (int targetRowIndex = 0; targetRowIndex < n; targetRowIndex++) {
      int requiredTrailingZeros = n - 1 - targetRowIndex;

      int candidateRowIndex = targetRowIndex;
      while (candidateRowIndex < n
          && trailingZerosPerRow[candidateRowIndex] < requiredTrailingZeros) {
        candidateRowIndex++;
      }

      if (candidateRowIndex == n) {
        return -1;
      }

      while (candidateRowIndex > targetRowIndex) {
        int temp = trailingZerosPerRow[candidateRowIndex];
        trailingZerosPerRow[candidateRowIndex] = trailingZerosPerRow[candidateRowIndex - 1];
        trailingZerosPerRow[candidateRowIndex - 1] = temp;

        totalSwaps++;
        candidateRowIndex--;
      }
    }

    return totalSwaps;
  }
}
