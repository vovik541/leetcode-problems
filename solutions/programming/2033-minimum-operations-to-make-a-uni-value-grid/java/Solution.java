package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int minOperations(int[][] grid, int x) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;
    int totalElements = rowCount * columnCount;

    int[] flattenedValues = new int[totalElements];
    int currentIndex = 0;

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
        flattenedValues[currentIndex++] = grid[rowIndex][columnIndex];
      }
    }

    int remainder = flattenedValues[0] % x;
    for (int value : flattenedValues) {
      if (value % x != remainder) {
        return -1;
      }
    }

    Arrays.sort(flattenedValues);

    int medianValue = flattenedValues[totalElements / 2];

    int totalOperations = 0;
    for (int value : flattenedValues) {
      totalOperations += Math.abs(value - medianValue) / x;
    }

    return totalOperations;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] grid1 = {
      {2, 4},
      {6, 8}
    };
    System.out.println(solution.minOperations(grid1, 2)); // 4

    int[][] grid2 = {
      {1, 5},
      {2, 3}
    };
    System.out.println(solution.minOperations(grid2, 1)); // 5

    int[][] grid3 = {
      {1, 2},
      {3, 4}
    };
    System.out.println(solution.minOperations(grid3, 2)); // -1
  }
}
