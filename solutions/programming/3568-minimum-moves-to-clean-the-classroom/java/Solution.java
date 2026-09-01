package dev.vkh.solutions;

class Solution {

  private static final int[][] DIRECTIONS = {
    {1, 0},
    {-1, 0},
    {0, 1},
    {0, -1}
  };

  public static int minMoves(String[] classroom, int energy) {
    int rows = classroom.length;
    int columns = classroom[0].length();

    int startRow = 0;
    int startColumn = 0;
    int litterCount = 0;

    int[][] litterIndex = new int[rows][columns];

    for (int row = 0; row < rows; row++) {
      java.util.Arrays.fill(litterIndex[row], -1);

      for (int column = 0; column < columns; column++) {
        char cell = classroom[row].charAt(column);

        if (cell == 'S') {
          startRow = row;
          startColumn = column;
        } else if (cell == 'L') {
          litterIndex[row][column] = litterCount++;
        }
      }
    }

    int fullMask = (1 << litterCount) - 1;

    if (fullMask == 0) {
      return 0;
    }

    int[][][] bestEnergy = new int[rows][columns][1 << litterCount];

    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        java.util.Arrays.fill(bestEnergy[row][column], -1);
      }
    }

    java.util.Queue<int[]> queue = new java.util.ArrayDeque<>();

    queue.offer(new int[] {startRow, startColumn, 0, energy});
    bestEnergy[startRow][startColumn][0] = energy;

    int moves = 0;

    while (!queue.isEmpty()) {
      int levelSize = queue.size();

      for (int stateIndex = 0; stateIndex < levelSize; stateIndex++) {
        int[] state = queue.poll();

        int row = state[0];
        int column = state[1];
        int litterMask = state[2];
        int remainingEnergy = state[3];

        if (litterMask == fullMask) {
          return moves;
        }

        if (remainingEnergy == 0) {
          continue;
        }

        for (int[] direction : DIRECTIONS) {
          int nextRow = row + direction[0];
          int nextColumn = column + direction[1];

          if (nextRow < 0
              || nextRow >= rows
              || nextColumn < 0
              || nextColumn >= columns
              || classroom[nextRow].charAt(nextColumn) == 'X') {
            continue;
          }

          int nextEnergy = remainingEnergy - 1;
          int nextMask = litterMask;

          if (litterIndex[nextRow][nextColumn] != -1) {
            nextMask |= 1 << litterIndex[nextRow][nextColumn];
          }

          if (classroom[nextRow].charAt(nextColumn) == 'R') {
            nextEnergy = energy;
          }

          if (nextEnergy <= bestEnergy[nextRow][nextColumn][nextMask]) {
            continue;
          }

          bestEnergy[nextRow][nextColumn][nextMask] = nextEnergy;

          queue.offer(new int[] {nextRow, nextColumn, nextMask, nextEnergy});
        }
      }

      moves++;
    }

    return -1;
  }

  static void main() {
    System.out.println(minMoves(new String[] {"S.", "XL"}, 2)); // 2
  }
}
