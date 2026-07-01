package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Solution {

  private static final int[][] DIRECTIONS = {
    {1, 0},
    {-1, 0},
    {0, 1},
    {0, -1}
  };

  public static int maximumSafenessFactor(List<List<Integer>> grid) {
    int n = grid.size();

    int[][] distanceToNearestThief = calculateDistanceToNearestThief(grid);

    int left = 0;
    int right = 2 * n;
    int bestSafenessFactor = 0;

    while (left <= right) {
      int middleSafenessFactor = left + (right - left) / 2;

      if (canReachEndWithSafeness(distanceToNearestThief, middleSafenessFactor)) {
        bestSafenessFactor = middleSafenessFactor;
        left = middleSafenessFactor + 1;
      } else {
        right = middleSafenessFactor - 1;
      }
    }

    return bestSafenessFactor;
  }

  private static int[][] calculateDistanceToNearestThief(List<List<Integer>> grid) {
    int n = grid.size();

    int[][] distance = new int[n][n];

    for (int[] row : distance) {
      Arrays.fill(row, -1);
    }

    Queue<int[]> queue = new ArrayDeque<>();

    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        if (grid.get(row).get(col) == 1) {
          distance[row][col] = 0;
          queue.offer(new int[] {row, col});
        }
      }
    }

    while (!queue.isEmpty()) {
      int[] currentCell = queue.poll();

      int currentRow = currentCell[0];
      int currentCol = currentCell[1];

      for (int[] direction : DIRECTIONS) {
        int nextRow = currentRow + direction[0];
        int nextCol = currentCol + direction[1];

        if (isOutsideGrid(nextRow, nextCol, n) || distance[nextRow][nextCol] != -1) {
          continue;
        }

        distance[nextRow][nextCol] = distance[currentRow][currentCol] + 1;
        queue.offer(new int[] {nextRow, nextCol});
      }
    }

    return distance;
  }

  private static boolean canReachEndWithSafeness(
      int[][] distanceToNearestThief, int requiredSafeness) {
    int n = distanceToNearestThief.length;

    if (distanceToNearestThief[0][0] < requiredSafeness
        || distanceToNearestThief[n - 1][n - 1] < requiredSafeness) {
      return false;
    }

    boolean[][] visited = new boolean[n][n];
    Queue<int[]> queue = new ArrayDeque<>();

    visited[0][0] = true;
    queue.offer(new int[] {0, 0});

    while (!queue.isEmpty()) {
      int[] currentCell = queue.poll();

      int currentRow = currentCell[0];
      int currentCol = currentCell[1];

      if (currentRow == n - 1 && currentCol == n - 1) {
        return true;
      }

      for (int[] direction : DIRECTIONS) {
        int nextRow = currentRow + direction[0];
        int nextCol = currentCol + direction[1];

        if (isOutsideGrid(nextRow, nextCol, n)
            || visited[nextRow][nextCol]
            || distanceToNearestThief[nextRow][nextCol] < requiredSafeness) {
          continue;
        }

        visited[nextRow][nextCol] = true;
        queue.offer(new int[] {nextRow, nextCol});
      }
    }

    return false;
  }

  private static boolean isOutsideGrid(int row, int col, int n) {
    return row < 0 || row >= n || col < 0 || col >= n;
  }

  static void main() {
    List<List<Integer>> grid1 = List.of(List.of(1, 0, 0), List.of(0, 0, 0), List.of(0, 0, 1));
    System.out.println(maximumSafenessFactor(grid1)); // Output: 0

    List<List<Integer>> grid2 = List.of(List.of(0, 0, 1), List.of(0, 0, 0), List.of(0, 0, 0));
    System.out.println(maximumSafenessFactor(grid2)); // Output: 2
  }
}
