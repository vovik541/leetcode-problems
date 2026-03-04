package dev.vkh.solutions;

class Solution {

  public int numSpecial(int[][] mat) {
    int specialPositionsCount = 0;

    nextRow:
    for (int rowIndex = 0; rowIndex < mat.length; rowIndex++) {
      boolean foundOneInRow = false;
      int candidateColumnIndex = -1;

      for (int colIndex = 0; colIndex < mat[rowIndex].length; colIndex++) {
        if (mat[rowIndex][colIndex] == 1) {
          if (foundOneInRow) {
            continue nextRow;
          }
          foundOneInRow = true;
          candidateColumnIndex = colIndex;
        }
      }

      if (!foundOneInRow) {
        continue;
      }

      for (int checkRowIndex = 0; checkRowIndex < mat.length; checkRowIndex++) {
        if (checkRowIndex != rowIndex && mat[checkRowIndex][candidateColumnIndex] == 1) {
          continue nextRow;
        }
      }

      specialPositionsCount++;
    }

    return specialPositionsCount;
  }

  static void main() {
    System.out.println(
        new Solution()
            .numSpecial(
                new int[][] {
                  {1, 0, 0},
                  {0, 0, 1},
                  {1, 0, 0}
                })); // 1
  }
}
