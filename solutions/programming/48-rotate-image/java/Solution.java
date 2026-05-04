package dev.vkh.solutions;

class Solution {

  public void rotate(int[][] matrix) {
    int matrixSize = matrix.length;

    for (int rowIndex = 0; rowIndex < matrixSize; rowIndex++) {
      for (int columnIndex = rowIndex; columnIndex < matrixSize; columnIndex++) {
        int temp = matrix[rowIndex][columnIndex];
        matrix[rowIndex][columnIndex] = matrix[columnIndex][rowIndex];
        matrix[columnIndex][rowIndex] = temp;
      }
    }

    for (int rowIndex = 0; rowIndex < matrixSize; rowIndex++) {
      int left = 0;
      int right = matrixSize - 1;

      while (left < right) {
        int temp = matrix[rowIndex][left];
        matrix[rowIndex][left] = matrix[rowIndex][right];
        matrix[rowIndex][right] = temp;

        left++;
        right--;
      }
    }
  }

  private static void printMatrix(int[][] matrix) {
    for (int[] row : matrix) {
      System.out.print("[");
      for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
        System.out.print(row[columnIndex]);
        if (columnIndex + 1 < row.length) {
          System.out.print(", ");
        }
      }
      System.out.println("]");
    }
    System.out.println();
  }

  static void main() {
    Solution solution = new Solution();

    int[][] matrix1 = {
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9}
    };
    solution.rotate(matrix1);
    printMatrix(matrix1);
    // [[7,4,1],[8,5,2],[9,6,3]]
  }
}
