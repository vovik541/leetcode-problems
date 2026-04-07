package dev.vkh.solutions;

class Robot {

  int w, h;
  int x, y;
  int dir; // 0=East, 1=North, 2=West, 3=South
  int per;

  public Robot(int width, int height) {
    this.w = width;
    this.h = height;
    this.x = 0;
    this.y = 0;
    this.dir = 0;
    this.per = 2 * (w + h) - 4;
  }

  public void step(int num) {
    if (per == 0) return;

    num %= per;

    if (num == 0) {
      if (x == 0 && y == 0) {
        dir = 3;
      }
      return;
    }

    while (num > 0) {
      if (dir == 0) {
        int move = Math.min(num, w - 1 - x);
        x += move;
        num -= move;
        if (num > 0) dir = 1;
      } else if (dir == 1) {
        int move = Math.min(num, h - 1 - y);
        y += move;
        num -= move;
        if (num > 0) dir = 2;
      } else if (dir == 2) {
        int move = Math.min(num, x);
        x -= move;
        num -= move;
        if (num > 0) dir = 3;
      } else {
        int move = Math.min(num, y);
        y -= move;
        num -= move;
        if (num > 0) dir = 0;
      }
    }
  }

  public int[] getPos() {
    return new int[] {x, y};
  }

  public String getDir() {
    if (dir == 0) return "East";
    if (dir == 1) return "North";
    if (dir == 2) return "West";
    return "South";
  }
}

/**
 * Your Robot object will be instantiated and called as such: Robot obj = new Robot(width, height);
 * obj.step(num); int[] param_2 = obj.getPos(); String param_3 = obj.getDir();
 */
