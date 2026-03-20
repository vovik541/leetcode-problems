package dev.vkh.solutions;

class Solution {

  public int numberOfSubmatrices(char[][] grid) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    int[] balanceByColumn = new int[columnCount];
    int[] xCountByColumn = new int[columnCount];

    int validSubmatrixCount = 0;

    for (int row = 0; row < rowCount; row++) {
      int prefixBalance = 0;
      int prefixXCount = 0;

      for (int column = 0; column < columnCount; column++) {
        char currentCell = grid[row][column];

        if (currentCell == 'X') {
          balanceByColumn[column] += 1;
          xCountByColumn[column] += 1;
        } else if (currentCell == 'Y') {
          balanceByColumn[column] -= 1;
        }

        prefixBalance += balanceByColumn[column];
        prefixXCount += xCountByColumn[column];

        if (prefixBalance == 0 && prefixXCount > 0) {
          validSubmatrixCount++;
        }
      }
    }

    return validSubmatrixCount;
  }

  static void main() {
    System.out.println(
        new Solution().numberOfSubmatrices(new char[][] {{'X', 'Y', '.'}, {'Y', '.', '.'}})); // 3
  }
}
