package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution {

  private static final int[][] DIRECTIONS = {
    {1, 0},
    {-1, 0},
    {0, 1},
    {0, -1}
  };

  public static boolean findSafeWalk(List<List<Integer>> grid, int health) {
    int rowCount = grid.size();
    int colCount = grid.get(0).size();

    int[][] minDamage = new int[rowCount][colCount];

    for (int[] row : minDamage) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }

    Deque<int[]> deque = new ArrayDeque<>();

    minDamage[0][0] = grid.get(0).get(0);
    deque.offerFirst(new int[] {0, 0});

    while (!deque.isEmpty()) {
      int[] currentCell = deque.pollFirst();

      int currentRow = currentCell[0];
      int currentCol = currentCell[1];

      for (int[] direction : DIRECTIONS) {
        int nextRow = currentRow + direction[0];
        int nextCol = currentCol + direction[1];

        if (isOutsideGrid(nextRow, nextCol, rowCount, colCount)) {
          continue;
        }

        int extraDamage = grid.get(nextRow).get(nextCol);
        int newDamage = minDamage[currentRow][currentCol] + extraDamage;

        if (newDamage >= minDamage[nextRow][nextCol]) {
          continue;
        }

        minDamage[nextRow][nextCol] = newDamage;

        if (extraDamage == 0) {
          deque.offerFirst(new int[] {nextRow, nextCol});
        } else {
          deque.offerLast(new int[] {nextRow, nextCol});
        }
      }
    }

    return minDamage[rowCount - 1][colCount - 1] < health;
  }

  private static boolean isOutsideGrid(int row, int col, int rowCount, int colCount) {
    return row < 0 || row >= rowCount || col < 0 || col >= colCount;
  }

  static void main() {
    List<List<Integer>> grid1 =
        List.of(List.of(0, 1, 0, 0, 0), List.of(0, 1, 0, 1, 0), List.of(0, 0, 0, 1, 0));
    System.out.println(findSafeWalk(grid1, 1)); // Output: true

    List<List<Integer>> grid2 =
        List.of(
            List.of(0, 1, 1, 0, 0, 0),
            List.of(1, 0, 1, 0, 0, 0),
            List.of(0, 1, 1, 1, 0, 1),
            List.of(0, 0, 1, 0, 1, 0));
    System.out.println(findSafeWalk(grid2, 3)); // Output: false
  }
}
