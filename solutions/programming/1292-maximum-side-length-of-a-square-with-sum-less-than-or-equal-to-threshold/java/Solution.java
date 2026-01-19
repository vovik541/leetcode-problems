package dev.vkh.solutions;

class Solution {

  static void main(String[] args) {
    Solution solution = new Solution();

    int[][] mat = {
      {1, 1, 3, 2, 4, 3, 2},
      {1, 1, 3, 2, 4, 3, 2},
      {1, 1, 3, 2, 4, 3, 2}
    };
    //    int[][] mat = {
    //            {2, 2, 2, 2, 2},
    //            {2, 2, 2, 2, 2},
    //            {2, 2, 2, 2, 2},
    //            {2, 2, 2, 2, 2},
    //            {2, 2, 2, 2, 2}
    //    };
    int threshold1 = 4;
    System.out.println(solution.maxSideLength(mat, threshold1));
  }

  public int maxSideLength(int[][] mat, int threshold) {
    int rowCount = mat.length;
    int colCount = mat[0].length;

    int[][] prefixSum = buildPrefixSum(mat);

    int low = 0;
    int high = Math.min(rowCount, colCount);

    while (low < high) {
      int mid = low + (high - low + 1) / 2;
      if (existsSquareWithSumAtMost(prefixSum, mid, threshold)) {
        low = mid;
      } else {
        high = mid - 1;
      }
    }

    return low;
  }

  private int[][] buildPrefixSum(int[][] mat) {
    int rowCount = mat.length;
    int colCount = mat[0].length;

    int[][] prefixSum = new int[rowCount + 1][colCount + 1];

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int colIndex = 0; colIndex < colCount; colIndex++) {
        prefixSum[rowIndex + 1][colIndex + 1] =
            prefixSum[rowIndex][colIndex + 1]
                + prefixSum[rowIndex + 1][colIndex]
                - prefixSum[rowIndex][colIndex]
                + mat[rowIndex][colIndex];
      }
    }

    return prefixSum;
  }

  private boolean existsSquareWithSumAtMost(int[][] prefixSum, int sideLength, int threshold) {
    if (sideLength == 0) return true;

    int rowCount = prefixSum.length - 1;
    int colCount = prefixSum[0].length - 1;

    for (int topRow = 0; topRow + sideLength <= rowCount; topRow++) {
      int bottomRowExclusive = topRow + sideLength;

      for (int leftCol = 0; leftCol + sideLength <= colCount; leftCol++) {
        int rightColExclusive = leftCol + sideLength;

        int squareSum =
            getRectangleSum(prefixSum, topRow, leftCol, bottomRowExclusive, rightColExclusive);
        if (squareSum <= threshold) {
          return true;
        }
      }
    }

    return false;
  }

  private int getRectangleSum(
      int[][] prefixSum, int topRow, int leftCol, int bottomRowExclusive, int rightColExclusive) {
    return prefixSum[bottomRowExclusive][rightColExclusive]
        - prefixSum[topRow][rightColExclusive]
        - prefixSum[bottomRowExclusive][leftCol]
        + prefixSum[topRow][leftCol];
  }
}
