package dev.vkh.solutions;

class Solution {
  public char[][] rotateTheBox(char[][] boxGrid) {
    int stonesSpotted = 0;
    for (int row = 0; row < boxGrid.length; row++) {
      search:
      for (int index = 0; index < boxGrid[row].length; index++) {

        switch (boxGrid[row][index]) {
          case '#':
            stonesSpotted++;
            boxGrid[row][index] = '.';
          case '.':
            continue search;
          case '*':
            placeStones(boxGrid, stonesSpotted, row, index);
            stonesSpotted = 0;
        }
      }
      if (stonesSpotted > 0) {
        placeStones(boxGrid, stonesSpotted, row, boxGrid[row].length);
        stonesSpotted = 0;
      }
    }
    return rotate(boxGrid);
  }

  private void placeStones(char[][] boxGrid, int stonesSpotted, int rowIndex, int obstacleIndex) {
    obstacleIndex--;
    while (stonesSpotted > 0) {
      boxGrid[rowIndex][obstacleIndex] = '#';
      stonesSpotted--;
      obstacleIndex--;
    }
  }

  private char[][] rotate(char[][] boxGrid) {
    char[][] rotatedGrid = new char[boxGrid[0].length][boxGrid.length];
    for (int row = 0; row < boxGrid.length; row++) {
      for (int column = 0; column < boxGrid[row].length; column++) {
        rotatedGrid[column][boxGrid.length - 1 - row] = boxGrid[row][column];
      }
    }
    return rotatedGrid;
  }

  static void main() {
    char[][] boxGrid = new char[][] {{'#', '.', '*', '.'}, {'#', '#', '*', '.'}};
    char[][] result = new Solution().rotateTheBox(boxGrid);

    for (char[] row : result) {
      for (char column : row) {
        System.out.print(" " + column);
      }
      System.out.println();
    }
  }
}
