package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

  public static int countIslands(int[][] grid, int k) {
    int rows = grid.length;
    int columns = grid[0].length;
    int result = 0;

    int[] directions = {-1, 0, 1, 0, -1};

    Deque<Integer> stack = new ArrayDeque<>();

    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        if (grid[row][column] == 0) {
          continue;
        }

        long islandSum = grid[row][column];
        grid[row][column] = 0;

        stack.push(row * columns + column);

        while (!stack.isEmpty()) {
          int position = stack.pop();

          int currentRow = position / columns;
          int currentColumn = position % columns;

          for (int direction = 0; direction < 4; direction++) {
            int nextRow = currentRow + directions[direction];
            int nextColumn = currentColumn + directions[direction + 1];

            if (nextRow < 0
                || nextRow >= rows
                || nextColumn < 0
                || nextColumn >= columns
                || grid[nextRow][nextColumn] == 0) {
              continue;
            }

            islandSum += grid[nextRow][nextColumn];
            grid[nextRow][nextColumn] = 0;

            stack.push(nextRow * columns + nextColumn);
          }
        }

        if (islandSum % k == 0) {
          result++;
        }
      }
    }

    return result;
  }

  static void main() {
    System.out.println(
        countIslands(
            new int[][] {
              {0, 2, 1, 0, 0},
              {0, 5, 0, 0, 5},
              {0, 0, 1, 0, 0},
              {0, 1, 4, 7, 0},
              {0, 2, 0, 0, 8}
            },
            5)); // 2
    System.out.println(
        countIslands(
            new int[][] {
              {3, 0, 3, 0},
              {0, 3, 0, 3},
              {3, 0, 3, 0}
            },
            3)); // 6
    System.out.println(
        countIslands(
            new int[][] {
              {1, 2},
              {3, 4}
            },
            5)); // 1
    System.out.println(
        countIslands(
            new int[][] {
              {0, 0},
              {0, 0}
            },
            2)); // 0
    System.out.println(countIslands(new int[][] {{7}}, 7)); // 1
  }
}
