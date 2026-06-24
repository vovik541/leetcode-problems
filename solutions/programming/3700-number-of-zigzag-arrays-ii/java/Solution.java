package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {

  private static final long MOD = 1_000_000_007L;

  public static int zigZagArrays(int n, int l, int r) {
    int valueCount = r - l + 1;
    int stateCount = valueCount * 2;

    long[][] transitionMatrix = buildTransitionMatrix(valueCount);

    long[] currentVector = new long[stateCount];
    Arrays.fill(currentVector, 1L);

    long[] resultVector = multiplyPowerByVector(transitionMatrix, currentVector, n - 1);

    long answer = 0;

    for (long count : resultVector) {
      answer = (answer + count) % MOD;
    }

    return (int) answer;
  }

  private static long[][] buildTransitionMatrix(int valueCount) {
    int stateCount = valueCount * 2;

    long[][] matrix = new long[stateCount][stateCount];

    int downOffset = 0;
    int upOffset = valueCount;

    for (int currentValue = 0; currentValue < valueCount; currentValue++) {
      int currentDownState = downOffset + currentValue;
      int currentUpState = upOffset + currentValue;

      for (int nextValue = 0; nextValue < valueCount; nextValue++) {
        if (nextValue < currentValue) {
          int nextUpState = upOffset + nextValue;

          matrix[nextUpState][currentDownState] = 1;
        }

        if (nextValue > currentValue) {
          int nextDownState = downOffset + nextValue;

          matrix[nextDownState][currentUpState] = 1;
        }
      }
    }

    return matrix;
  }

  private static long[] multiplyPowerByVector(long[][] matrix, long[] vector, long power) {
    long[][] currentMatrix = matrix;
    long[] resultVector = vector;

    while (power > 0) {
      if ((power & 1L) == 1L) {
        resultVector = multiplyMatrixByVector(currentMatrix, resultVector);
      }

      currentMatrix = multiplyMatrices(currentMatrix, currentMatrix);
      power >>= 1;
    }

    return resultVector;
  }

  private static long[] multiplyMatrixByVector(long[][] matrix, long[] vector) {
    int size = vector.length;
    long[] result = new long[size];

    for (int row = 0; row < size; row++) {
      long sum = 0;

      for (int col = 0; col < size; col++) {
        if (matrix[row][col] == 0 || vector[col] == 0) {
          continue;
        }

        sum = (sum + matrix[row][col] * vector[col]) % MOD;
      }

      result[row] = sum;
    }

    return result;
  }

  private static long[][] multiplyMatrices(long[][] firstMatrix, long[][] secondMatrix) {
    int size = firstMatrix.length;
    long[][] result = new long[size][size];

    for (int row = 0; row < size; row++) {
      for (int middle = 0; middle < size; middle++) {
        if (firstMatrix[row][middle] == 0) {
          continue;
        }

        for (int col = 0; col < size; col++) {
          if (secondMatrix[middle][col] == 0) {
            continue;
          }

          result[row][col] =
              (result[row][col] + firstMatrix[row][middle] * secondMatrix[middle][col]) % MOD;
        }
      }
    }

    return result;
  }

  static void main() {
    int n = 3;
    int l = 4;
    int r = 5;
    System.out.println(zigZagArrays(n, l, r)); // Output: 2
  }
}
