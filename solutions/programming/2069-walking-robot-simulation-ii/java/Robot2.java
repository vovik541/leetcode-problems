package dev.vkh.solutions;

class Robot {

  private final int width;
  private final int height;
  private final int perimeter;

  private int x;
  private int y;
  private Direction direction;

  public Robot(int width, int height) {
    this.width = width;
    this.height = height;
    this.perimeter = 2 * (width + height) - 4;
    this.x = 0;
    this.y = 0;
    this.direction = Direction.EAST;
  }

  public void step(int steps) {
    if (perimeter == 0) {
      return;
    }

    steps %= perimeter;

    if (steps == 0) {
      return;
    }

    while (steps > 0) {
      switch (direction) {
        case EAST:
          {
            int availableMoves = (width - 1) - x;
            if (steps <= availableMoves) {
              x += steps;
              steps = 0;
            } else {
              x = width - 1;
              steps -= availableMoves;
              direction = Direction.NORTH;
            }
            break;
          }
        case NORTH:
          {
            int availableMoves = (height - 1) - y;
            if (steps <= availableMoves) {
              y += steps;
              steps = 0;
            } else {
              y = height - 1;
              steps -= availableMoves;
              direction = Direction.WEST;
            }
            break;
          }
        case WEST:
          {
            int availableMoves = x;
            if (steps <= availableMoves) {
              x -= steps;
              steps = 0;
            } else {
              x = 0;
              steps -= availableMoves;
              direction = Direction.SOUTH;
            }
            break;
          }
        case SOUTH:
          {
            int availableMoves = y;
            if (steps <= availableMoves) {
              y -= steps;
              steps = 0;
            } else {
              y = 0;
              steps -= availableMoves;
              direction = Direction.EAST;
            }
            break;
          }
      }
    }
  }

  public int[] getPos() {
    return new int[] {x, y};
  }

  public String getDir() {
    if (x == 0 && y == 0 && direction == Direction.EAST) {
      return "South";
    }
    return direction.displayName;
  }

  private enum Direction {
    EAST("East"),
    NORTH("North"),
    WEST("West"),
    SOUTH("South");

    private final String displayName;

    Direction(String displayName) {
      this.displayName = displayName;
    }
  }
}
