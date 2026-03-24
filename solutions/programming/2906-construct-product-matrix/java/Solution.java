package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {
  private static final int MODULO = 12345;

  public int[][] constructProductMatrix(int[][] grid) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    int[][] productMatrix = new int[rowCount][columnCount];

    long prefixProduct = 1L;

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
        productMatrix[rowIndex][columnIndex] = (int) prefixProduct;
        prefixProduct = (prefixProduct * grid[rowIndex][columnIndex]) % MODULO;
      }
    }

    long suffixProduct = 1L;

    for (int rowIndex = rowCount - 1; rowIndex >= 0; rowIndex--) {
      for (int columnIndex = columnCount - 1; columnIndex >= 0; columnIndex--) {
        productMatrix[rowIndex][columnIndex] =
            (int) ((productMatrix[rowIndex][columnIndex] * suffixProduct) % MODULO);

        suffixProduct = (suffixProduct * grid[rowIndex][columnIndex]) % MODULO;
      }
    }

    return productMatrix;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] firstGrid = {
      {1, 2},
      {3, 4}
    };

    int[][] secondGrid = {{12345}, {2}, {1}};

    int[][] firstAnswer = solution.constructProductMatrix(firstGrid);
    int[][] secondAnswer = solution.constructProductMatrix(secondGrid);

    printMatrix(firstAnswer); // [[24, 12], [8, 6]]
    printMatrix(secondAnswer); // [[2], [0], [0]]
  }

  private static void printMatrix(int[][] matrix) {
    System.out.print("[");
    for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
      System.out.print(Arrays.toString(matrix[rowIndex]));
      if (rowIndex != matrix.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }
}
