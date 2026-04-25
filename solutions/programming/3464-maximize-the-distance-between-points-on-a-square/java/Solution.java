package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int maxDistance(int side, int[][] points, int k) {
    int pointCount = points.length;
    long perimeter = 4L * side;

    long[] perimeterPositions = new long[pointCount];

    for (int index = 0; index < pointCount; index++) {
      perimeterPositions[index] = convertToPerimeterPosition(points[index], side);
    }

    Arrays.sort(perimeterPositions);

    long left = 0;
    long right = side;
    long bestDistance = 0;

    while (left <= right) {
      long middleDistance = left + (right - left) / 2;

      if (canSelectKPoints(perimeterPositions, perimeter, k, middleDistance)) {
        bestDistance = middleDistance;
        left = middleDistance + 1;
      } else {
        right = middleDistance - 1;
      }
    }

    return (int) bestDistance;
  }

  private long convertToPerimeterPosition(int[] point, int side) {
    int x = point[0];
    int y = point[1];

    if (y == 0) {
      return x;
    }

    if (x == side) {
      return (long) side + y;
    }

    if (y == side) {
      return 2L * side + (side - x);
    }

    return 3L * side + (side - y);
  }

  private boolean canSelectKPoints(
      long[] perimeterPositions, long perimeter, int k, long minimumDistance) {
    int pointCount = perimeterPositions.length;
    long[] doubledPositions = new long[pointCount * 2];

    for (int index = 0; index < pointCount; index++) {
      doubledPositions[index] = perimeterPositions[index];
      doubledPositions[index + pointCount] = perimeterPositions[index] + perimeter;
    }

    int[] nextValidIndex = new int[pointCount * 2];
    int nextIndex = 0;

    for (int index = 0; index < pointCount * 2; index++) {
      if (nextIndex < index + 1) {
        nextIndex = index + 1;
      }

      while (nextIndex < pointCount * 2
          && doubledPositions[nextIndex] - doubledPositions[index] < minimumDistance) {
        nextIndex++;
      }

      nextValidIndex[index] = nextIndex;
    }

    for (int startIndex = 0; startIndex < pointCount; startIndex++) {
      int currentIndex = startIndex;
      boolean canBuildSelection = true;

      for (int selectedCount = 1; selectedCount < k; selectedCount++) {
        currentIndex = nextValidIndex[currentIndex];

        if (currentIndex >= startIndex + pointCount) {
          canBuildSelection = false;
          break;
        }
      }

      if (canBuildSelection
          && doubledPositions[startIndex] + perimeter - doubledPositions[currentIndex]
              >= minimumDistance) {
        return true;
      }
    }

    return false;
  }

  static void main() {
    System.out.println(
        new Solution()
            .maxDistance(
                2, new int[][] {{0, 0}, {0, 1}, {0, 2}, {1, 2}, {2, 0}, {2, 2}, {2, 1}}, 5)); // 1
  }
}
