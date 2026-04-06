package dev.vkh.solutions;

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

      if (turnCommand == -1) { // turn right
        int nextIndex = (currentIndex + 1) % allDirections.length;
        return allDirections[nextIndex];
      }

      if (turnCommand == -2) { // turn left
        int nextIndex = (currentIndex - 1 + allDirections.length) % allDirections.length;
        return allDirections[nextIndex];
      }

      throw new IllegalArgumentException("Unsupported turn command: " + turnCommand);
    }
  }

  public int robotSim(int[] commands, int[][] obstacles) {
    Direction currentDirection = Direction.NORTH;
    int maxSquaredEuclideanDistance = 0;
    int currentX = 0;
    int currentY = 0;
    int nextX;
    int nextY;

    for (int command : commands) {
      if (command < 0) {
        currentDirection = currentDirection.turn(command);
        continue;
      }

      if (currentDirection.xDirection == 0) {
        nextY = currentY + currentDirection.yDirection * command;
        currentY = countCoordinate(currentX, currentY, currentX, nextY, obstacles);
      } else {
        nextX = currentX + currentDirection.xDirection * command;
        currentX = countCoordinate(currentX, currentY, nextX, currentY, obstacles);
      }

      maxSquaredEuclideanDistance =
          Math.max(currentX * currentX + currentY * currentY, maxSquaredEuclideanDistance);
    }

    return maxSquaredEuclideanDistance;
  }

  private static int countCoordinate(int oldX, int oldY, int newX, int newY, int[][] obstacles) {
    // vertical move
    if (oldX == newX) {
      int direction = Integer.compare(newY, oldY);
      int closestObstacleY = newY;

      for (int[] obstacle : obstacles) {
        int obstacleX = obstacle[0];
        int obstacleY = obstacle[1];

        if (obstacleX != oldX) {
          continue;
        }

        boolean isOnPath =
            (direction > 0 && obstacleY > oldY && obstacleY <= newY)
                || (direction < 0 && obstacleY < oldY && obstacleY >= newY);

        if (!isOnPath) {
          continue;
        }

        if (direction > 0) {
          closestObstacleY = Math.min(closestObstacleY, obstacleY);
        } else {
          closestObstacleY = Math.max(closestObstacleY, obstacleY);
        }
      }

      if (closestObstacleY != newY) {
        return closestObstacleY - direction;
      }

      return newY;
    }

    // horizontal move
    if (oldY == newY) {
      int direction = Integer.compare(newX, oldX);
      int closestObstacleX = newX;

      for (int[] obstacle : obstacles) {
        int obstacleX = obstacle[0];
        int obstacleY = obstacle[1];

        if (obstacleY != oldY) {
          continue;
        }

        boolean isOnPath =
            (direction > 0 && obstacleX > oldX && obstacleX <= newX)
                || (direction < 0 && obstacleX < oldX && obstacleX >= newX);

        if (!isOnPath) {
          continue;
        }

        if (direction > 0) {
          closestObstacleX = Math.min(closestObstacleX, obstacleX);
        } else {
          closestObstacleX = Math.max(closestObstacleX, obstacleX);
        }
      }

      if (closestObstacleX != newX) {
        return closestObstacleX - direction;
      }

      return newX;
    }

    throw new IllegalArgumentException("Movement must be only horizontal or vertical");
  }

  static void main() {
    System.out.println(new Solution().robotSim(new int[] {4, -1, 4, -2, 4}, new int[][] {{2, 4}}));
  }
}
