package dev.vkh.solutions;

class Solution {

  public int countSubmatrices(int[][] grid, int k) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    long[][] prefixSums = new long[rowCount + 1][columnCount + 1];
    int validSubmatricesCount = 0;

    for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {
      for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
        prefixSums[rowIndex][columnIndex] =
            grid[rowIndex - 1][columnIndex - 1]
                + prefixSums[rowIndex - 1][columnIndex]
                + prefixSums[rowIndex][columnIndex - 1]
                - prefixSums[rowIndex - 1][columnIndex - 1];

        if (prefixSums[rowIndex][columnIndex] <= k) {
          validSubmatricesCount++;
        }
      }
    }

    return validSubmatricesCount;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] grid = {
      {7, 6, 3},
      {6, 6, 1}
    };
    System.out.println(solution.countSubmatrices(grid, 18)); // 4

    grid =
        new int[][] {
          {7, 2, 9},
          {1, 5, 0},
          {2, 6, 6}
        };
    System.out.println(solution.countSubmatrices(grid, 20)); // 6
  }
}
