package dev.vkh.solutions;

import java.util.Arrays;
import java.util.List;

public class Solution {

  private static final int MOD = 1_000_000_007;
  private static final int NEGATIVE_INFINITY = -1_000_000_000;

  public static int[] pathsWithMaxScore(List<String> board) {
    int n = board.size();

    int[][] maxScore = new int[n][n];
    int[][] pathCount = new int[n][n];

    for (int[] row : maxScore) {
      Arrays.fill(row, NEGATIVE_INFINITY);
    }

    maxScore[0][0] = 0;
    pathCount[0][0] = 1;

    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        char currentCell = board.get(row).charAt(col);

        if (currentCell == 'X') {
          continue;
        }

        if (row == 0 && col == 0) {
          continue;
        }

        updateFromPreviousCell(row, col, row - 1, col, maxScore, pathCount);
        updateFromPreviousCell(row, col, row, col - 1, maxScore, pathCount);
        updateFromPreviousCell(row, col, row - 1, col - 1, maxScore, pathCount);

        if (pathCount[row][col] == 0) {
          continue;
        }

        if (currentCell >= '1' && currentCell <= '9') {
          maxScore[row][col] += currentCell - '0';
        }
      }
    }

    if (pathCount[n - 1][n - 1] == 0) {
      return new int[] {0, 0};
    }

    return new int[] {maxScore[n - 1][n - 1], pathCount[n - 1][n - 1]};
  }

  private static void updateFromPreviousCell(
      int currentRow,
      int currentCol,
      int previousRow,
      int previousCol,
      int[][] maxScore,
      int[][] pathCount) {
    if (previousRow < 0 || previousCol < 0) {
      return;
    }

    if (pathCount[previousRow][previousCol] == 0) {
      return;
    }

    int previousScore = maxScore[previousRow][previousCol];

    if (previousScore > maxScore[currentRow][currentCol]) {
      maxScore[currentRow][currentCol] = previousScore;
      pathCount[currentRow][currentCol] = pathCount[previousRow][previousCol];
    } else if (previousScore == maxScore[currentRow][currentCol]) {
      pathCount[currentRow][currentCol] =
          (pathCount[currentRow][currentCol] + pathCount[previousRow][previousCol]) % MOD;
    }
  }

  static void main() {
    List<String> board1 = List.of("E23", "2X2", "12S");
    System.out.println(Arrays.toString(pathsWithMaxScore(board1))); // Output: [7, 1]

    List<String> board2 = List.of("E12", "1X1", "21S");
    System.out.println(Arrays.toString(pathsWithMaxScore(board2))); // Output: [4, 2]
  }
}
