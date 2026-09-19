package dev.vkh.solutions;

class Solution {

  public static boolean checkOverlap(
      int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {

    int closestX = Math.max(x1, Math.min(xCenter, x2));
    int closestY = Math.max(y1, Math.min(yCenter, y2));

    long xDistance = xCenter - closestX;
    long yDistance = yCenter - closestY;

    long distanceSquared = xDistance * xDistance + yDistance * yDistance;

    return distanceSquared <= (long) radius * radius;
  }

  static void main() {
    System.out.println(checkOverlap(1, 0, 0, 1, -1, 3, 1)); // true
    System.out.println(checkOverlap(1, 1, 1, 1, -3, 2, -1)); // false
    System.out.println(checkOverlap(1, 0, 0, -1, 0, 0, 1)); // true
  }
}
