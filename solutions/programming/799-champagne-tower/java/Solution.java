package dev.vkh.solutions;

public class Solution {
  public double champagneTower(int poured, int query_row, int query_glass) {
    double[] currentRowAmounts = new double[query_row + 2];
    currentRowAmounts[0] = poured;

    for (int rowIndex = 0; rowIndex < query_row; rowIndex++) {
      double[] nextRowAmounts = new double[query_row + 2];

      for (int glassIndex = 0; glassIndex <= rowIndex; glassIndex++) {
        double overflow = Math.max(0.0, currentRowAmounts[glassIndex] - 1.0);
        double splitOverflow = overflow / 2.0;

        nextRowAmounts[glassIndex] += splitOverflow;
        nextRowAmounts[glassIndex + 1] += splitOverflow;
      }

      currentRowAmounts = nextRowAmounts;
    }

    return Math.min(1.0, currentRowAmounts[query_glass]);
  }

  static void main() {
    Solution solution = new Solution();
    System.out.println(solution.champagneTower(2, 1, 1));
  }
}
