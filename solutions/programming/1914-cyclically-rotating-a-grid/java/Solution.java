package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

class Solution {

  private int[][] originalGrid;
  private int rotationCount;

  public int[][] rotateGrid(int[][] grid, int k) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    this.originalGrid = grid;
    this.rotationCount = k;

    int[][] rotatedGrid = new int[rowCount][columnCount];

    int numberOfCycles = Math.min(rowCount, columnCount) / 2;

    for (int cycle = 0; cycle < numberOfCycles; cycle++) {
      fillCycle(cycle, rotatedGrid);
    }

    return rotatedGrid;
  }

  private List<Integer> getCycle(int cycle, int[][] grid) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;

    int topRow = cycle;
    int bottomRow = rowCount - 1 - cycle;
    int leftColumn = cycle;
    int rightColumn = columnCount - 1 - cycle;

    List<Integer> cycleValues = new ArrayList<>();

    for (int columnIndex = leftColumn; columnIndex <= rightColumn; columnIndex++) {
      cycleValues.add(grid[topRow][columnIndex]);
    }

    for (int rowIndex = topRow + 1; rowIndex <= bottomRow; rowIndex++) {
      cycleValues.add(grid[rowIndex][rightColumn]);
    }

    for (int columnIndex = rightColumn - 1; columnIndex >= leftColumn; columnIndex--) {
      cycleValues.add(grid[bottomRow][columnIndex]);
    }

    for (int rowIndex = bottomRow - 1; rowIndex > topRow; rowIndex--) {
      cycleValues.add(grid[rowIndex][leftColumn]);
    }

    return rotateCycle(cycleValues);
  }

  private void fillCycle(int cycle, int[][] rotatedGrid) {
    List<Integer> rotatedCycle = getCycle(cycle, originalGrid);

    int rowCount = rotatedGrid.length;
    int columnCount = rotatedGrid[0].length;

    int topRow = cycle;
    int bottomRow = rowCount - 1 - cycle;
    int leftColumn = cycle;
    int rightColumn = columnCount - 1 - cycle;

    int valueIndex = 0;

    for (int columnIndex = leftColumn; columnIndex <= rightColumn; columnIndex++) {
      rotatedGrid[topRow][columnIndex] = rotatedCycle.get(valueIndex++);
    }

    for (int rowIndex = topRow + 1; rowIndex <= bottomRow; rowIndex++) {
      rotatedGrid[rowIndex][rightColumn] = rotatedCycle.get(valueIndex++);
    }

    for (int columnIndex = rightColumn - 1; columnIndex >= leftColumn; columnIndex--) {
      rotatedGrid[bottomRow][columnIndex] = rotatedCycle.get(valueIndex++);
    }

    for (int rowIndex = bottomRow - 1; rowIndex > topRow; rowIndex--) {
      rotatedGrid[rowIndex][leftColumn] = rotatedCycle.get(valueIndex++);
    }
  }

  private List<Integer> rotateCycle(List<Integer> cycleValues) {
    int cycleLength = cycleValues.size();
    int effectiveRotationCount = rotationCount % cycleLength;

    List<Integer> rotatedCycleValues = new ArrayList<>(cycleLength);

    for (int index = 0; index < cycleLength; index++) {
      int sourceIndex = (index + effectiveRotationCount) % cycleLength;
      rotatedCycleValues.add(cycleValues.get(sourceIndex));
    }

    return rotatedCycleValues;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] grid = {
      {1, 2, 3, 4},
      {5, 6, 7, 8},
      {9, 10, 11, 12},
      {13, 14, 15, 16}
    };

    int[][] result = solution.rotateGrid(grid, 2);

    for (int[] row : result) {
      for (int value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }
}
