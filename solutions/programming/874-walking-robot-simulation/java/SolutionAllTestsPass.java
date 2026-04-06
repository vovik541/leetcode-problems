package dev.vkh.solutions;

import java.util.HashSet;
import java.util.Set;

class Solution {

  public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    private final int xDirection;
    private final int yDirection;

    Direction(int xDirection, int yDirection) {
      this.xDirection = xDirection;
      this.yDirection = yDirection;
    }

    public Direction turn(int turnCommand) {
      Direction[] allDirections = Direction.values();
      int currentIndex = this.ordinal();

      if (turnCommand == -1) { // right
        return allDirections[(currentIndex + 1) % allDirections.length];
      }

      if (turnCommand == -2) { // left
        return allDirections[(currentIndex - 1 + allDirections.length) % allDirections.length];
      }

      throw new IllegalArgumentException("Unsupported turn command: " + turnCommand);
    }
  }

  public int robotSim(int[] commands, int[][] obstacles) {
    Set<String> obstacleCoordinates = new HashSet<>();

    for (int[] obstacle : obstacles) {
      obstacleCoordinates.add(obstacle[0] + "," + obstacle[1]);
    }

    Direction currentDirection = Direction.NORTH;
    int currentX = 0;
    int currentY = 0;
    int maxSquaredEuclideanDistance = 0;

    for (int command : commands) {
      if (command < 0) {
        currentDirection = currentDirection.turn(command);
        continue;
      }

      for (int step = 0; step < command; step++) {
        int nextX = currentX + currentDirection.xDirection;
        int nextY = currentY + currentDirection.yDirection;

        if (obstacleCoordinates.contains(nextX + "," + nextY)) {
          break;
        }

        currentX = nextX;
        currentY = nextY;

        int currentSquaredDistance = currentX * currentX + currentY * currentY;
        maxSquaredEuclideanDistance = Math.max(maxSquaredEuclideanDistance, currentSquaredDistance);
      }
    }

    return maxSquaredEuclideanDistance;
  }

  static void main() {
    System.out.println(new Solution().robotSim(new int[] {4, -1, 4, -2, 4}, new int[][] {{2, 4}})); // 65
  }
}
