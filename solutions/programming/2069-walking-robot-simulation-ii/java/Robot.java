package dev.vkh.solutions;

import java.awt.*;

class Robot {

  Point current = new Point(0, 0);
  Direction direction = Direction.EAST;

  private int width;
  private int height;

  public Robot(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void step(int steps) {
    for (int step = 0; step < steps; step++) {
      switch (direction) {
        case EAST:
          if (current.x + 1 == width) {
            step--;
            direction = direction.turn();
            continue;
          }
          current.x++;
          break;
        case NORTH:
          if (current.y + 1 == height) {
            step--;
            direction = direction.turn();
            continue;
          }
          current.y++;
          break;
        case WEST:
          if (current.x - 1 == -1) {
            step--;
            direction = direction.turn();
            continue;
          }
          current.x--;
          break;
        case SOUTH:
          if (current.y - 1 == -1) {
            step--;
            direction = direction.turn();
            continue;
          }
          current.y--;
          break;
      }
    }
  }

  public int[] getPos() {
    return new int[] {current.x, current.y};
  }

  public String getDir() {
    return direction.displayName;
  }

  private enum Direction {
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West");

    private final String displayName;

    Direction(String name) {
      this.displayName = name;
    }

    public Direction turn() {
      Direction[] allDirections = Direction.values();
      int currentIndex = this.ordinal();

      return allDirections[(currentIndex - 1 + allDirections.length) % allDirections.length];
    }
  }
}
