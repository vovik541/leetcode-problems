package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

  public int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }

    int rowCount = matrix.length;
    int columnCount = matrix[0].length;

    int[] heights = new int[columnCount];
    int maxArea = 0;

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
        if (matrix[rowIndex][columnIndex] == '1') {
          heights[columnIndex] += 1;
        } else {
          heights[columnIndex] = 0;
        }
      }

      maxArea = Math.max(maxArea, largestRectangleArea(heights));
    }

    return maxArea;
  }

  private int largestRectangleArea(int[] heights) {
    int length = heights.length;
    int bestArea = 0;

    Deque<Integer> indexStack = new ArrayDeque<>();

    for (int index = 0; index <= length; index++) {
      int currentHeight = (index == length) ? 0 : heights[index];

      while (!indexStack.isEmpty() && heights[indexStack.peek()] > currentHeight) {
        int heightOfBar = heights[indexStack.pop()];
        int rightBoundaryExclusive = index;
        int leftBoundaryExclusive = indexStack.isEmpty() ? -1 : indexStack.peek();

        int width = rightBoundaryExclusive - leftBoundaryExclusive - 1;
        bestArea = Math.max(bestArea, heightOfBar * width);
      }

      indexStack.push(index);
    }

    return bestArea;
  }

  static void main(String[] args) {
    Solution solution = new Solution();

    char[][] matrix1 = {
      {'1', '0', '1', '0', '0'},
      {'1', '0', '1', '1', '1'},
      {'1', '1', '1', '1', '1'},
      {'1', '0', '0', '1', '0'}
    };

    char[][] matrix2 = {{'0'}};

    char[][] matrix3 = {{'1'}};

    System.out.println(solution.maximalRectangle(matrix1)); // 6
    System.out.println(solution.maximalRectangle(matrix2)); // 0
    System.out.println(solution.maximalRectangle(matrix3)); // 1
  }
}
