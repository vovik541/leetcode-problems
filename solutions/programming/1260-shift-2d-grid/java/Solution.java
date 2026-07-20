package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

class Solution {

  public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
    int rowCount = grid.length;
    int colCount = grid[0].length;
    int totalElements = rowCount * colCount;

    k = k % totalElements;

    int[][] shifted = new int[rowCount][colCount];

    for (int oldIndex = 0; oldIndex < totalElements; oldIndex++) {
      int oldRow = oldIndex / colCount;
      int oldCol = oldIndex % colCount;

      int newIndex = (oldIndex + k) % totalElements;
      int newRow = newIndex / colCount;
      int newCol = newIndex % colCount;

      shifted[newRow][newCol] = grid[oldRow][oldCol];
    }

    List<List<Integer>> result = new ArrayList<>();

    for (int row = 0; row < rowCount; row++) {
      List<Integer> currentRow = new ArrayList<>();

      for (int col = 0; col < colCount; col++) {
        currentRow.add(shifted[row][col]);
      }

      result.add(currentRow);
    }

    return result;
  }

  static void main() {
    System.out.println(
        shiftGrid(
            new int[][] {{3, 8, 1, 9}, {19, 7, 2, 5}, {4, 6, 11, 10}, {12, 0, 21, 13}},
            4)); // [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
    System.out.println(
        shiftGrid(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 9)); // [[1,2,3],[4,5,6],[7,8,9]]
  }
}
