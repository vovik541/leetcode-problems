package dev.vkh.solutions;

class Solution {

  public boolean areSimilar(int[][] mat, int k) {
    int rowCount = mat.length;
    int columnCount = mat[0].length;
    int effectiveShiftCount = k % columnCount;

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
        int shiftedColumnIndex;

        if (rowIndex % 2 == 0) {
          shiftedColumnIndex = (columnIndex + effectiveShiftCount) % columnCount;
        } else {
          shiftedColumnIndex = (columnIndex - effectiveShiftCount + columnCount) % columnCount;
        }

        if (mat[rowIndex][columnIndex] != mat[rowIndex][shiftedColumnIndex]) {
          return false;
        }
      }
    }

    return true;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] mat = {
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9}
    };
    System.out.println(solution.areSimilar(mat, 4)); // false
  }
}
