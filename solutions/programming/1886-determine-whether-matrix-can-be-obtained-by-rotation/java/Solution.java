package dev.vkh.solutions;

class Solution {
  public boolean findRotation(int[][] matrix, int[][] targetMatrix) {
    for (int rotationCount = 0; rotationCount < 4; rotationCount++) {
      if (areMatricesEqual(matrix, targetMatrix)) {
        return true;
      }
      matrix = rotateClockwise90Degrees(matrix);
    }

    return false;
  }

  private boolean areMatricesEqual(int[][] firstMatrix, int[][] secondMatrix) {
    int matrixSize = firstMatrix.length;

    for (int row = 0; row < matrixSize; row++) {
      for (int column = 0; column < matrixSize; column++) {
        if (firstMatrix[row][column] != secondMatrix[row][column]) {
          return false;
        }
      }
    }

    return true;
  }

  private int[][] rotateClockwise90Degrees(int[][] matrix) {
    int matrixSize = matrix.length;
    int[][] rotatedMatrix = new int[matrixSize][matrixSize];

    for (int row = 0; row < matrixSize; row++) {
      for (int column = 0; column < matrixSize; column++) {
        rotatedMatrix[column][matrixSize - 1 - row] = matrix[row][column];
      }
    }

    return rotatedMatrix;
  }

  static void main() {
    int[][] mat = {
      {1, 0},
      {0, 1},
    };
    int[][] target = {
      {0, 1},
      {1, 0},
    };
    System.out.println(new Solution().findRotation(mat, target));
  }
}
