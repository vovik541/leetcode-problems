package dev.vkh.solutions;

import java.util.*;

public class Solution {

  static void main(String[] args) {
    Solution solution = new Solution();

    int[][] squares1 = {{0, 0, 1}, {2, 2, 1}};
    System.out.printf(Locale.US, "%.5f%n", solution.separateSquares(squares1)); // ~1.00000

    int[][] squares2 = {{0, 0, 2}, {1, 1, 1}};
    System.out.printf(Locale.US, "%.5f%n", solution.separateSquares(squares2)); // ~1.16667
  }

  public double separateSquares(int[][] squares) {
    double totalArea = 0.0;

    double lowY = Double.POSITIVE_INFINITY;
    double highY = Double.NEGATIVE_INFINITY;

    for (int[] square : squares) {
      long bottomY = square[1];
      long sideLength = square[2];

      totalArea += (double) sideLength * (double) sideLength;

      lowY = Math.min(lowY, bottomY);
      highY = Math.max(highY, bottomY + (double) sideLength);
    }

    double halfArea = totalArea / 2.0;

    // Binary search for the minimal y such that areaBelow(y) >= halfArea
    double left = lowY;
    double right = highY;

    for (int iteration = 0; iteration < 50; iteration++) {
      double mid = (left + right) / 2.0;
      double areaBelowMid = computeAreaBelow(squares, mid);

      if (areaBelowMid < halfArea) {
        left = mid;
      } else {
        right = mid;
      }
    }

    return right;
  }

  private double computeAreaBelow(int[][] squares, double lineY) {
    double sumAreaBelow = 0.0;

    for (int[] square : squares) {
      double bottomY = square[1];
      double sideLength = square[2];
      double topY = bottomY + sideLength;

      if (lineY <= bottomY) {
        continue;
      }
      if (lineY >= topY) {
        sumAreaBelow += sideLength * sideLength;
        continue;
      }

      double heightBelow = lineY - bottomY;
      sumAreaBelow += heightBelow * sideLength;
    }

    return sumAreaBelow;
  }
}
