package dev.vkh.solutions;

import java.util.*;
import java.util.Arrays;

class Solution {

  public int maxWalls(int[] robots, int[] distance, int[] walls) {
    int robotCount = robots.length;

    int[][] robotInfo = new int[robotCount][2];
    for (int index = 0; index < robotCount; index++) {
      robotInfo[index][0] = robots[index];
      robotInfo[index][1] = distance[index];
    }

    Arrays.sort(robotInfo, (first, second) -> Integer.compare(first[0], second[0]));
    Arrays.sort(walls);

    int[] sortedRobots = new int[robotCount];
    int[] sortedDistance = new int[robotCount];

    for (int index = 0; index < robotCount; index++) {
      sortedRobots[index] = robotInfo[index][0];
      sortedDistance[index] = robotInfo[index][1];
    }

    int[] leftDestroyedCount = new int[robotCount];
    int[] rightDestroyedCount = new int[robotCount];

    long[] leftRangeStart = new long[robotCount];
    long[] leftRangeEnd = new long[robotCount];
    long[] rightRangeStart = new long[robotCount];
    long[] rightRangeEnd = new long[robotCount];

    for (int index = 0; index < robotCount; index++) {
      long robotPosition = sortedRobots[index];
      long maxDistance = sortedDistance[index];

      long leftBoundary = robotPosition - maxDistance;
      if (index > 0) {
        leftBoundary = Math.max(leftBoundary, sortedRobots[index - 1]);
      }

      long rightBoundary = robotPosition + maxDistance;
      if (index + 1 < robotCount) {
        rightBoundary = Math.min(rightBoundary, sortedRobots[index + 1]);
      }

      leftRangeStart[index] = leftBoundary;
      leftRangeEnd[index] = robotPosition;

      rightRangeStart[index] = robotPosition;
      rightRangeEnd[index] = rightBoundary;

      leftDestroyedCount[index] = countWallsInRange(walls, leftBoundary, robotPosition);
      rightDestroyedCount[index] = countWallsInRange(walls, robotPosition, rightBoundary);
    }

    long bestIfShootLeft = leftDestroyedCount[0];
    long bestIfShootRight = rightDestroyedCount[0];

    for (int index = 1; index < robotCount; index++) {
      long overlapWalls =
          countWallsInRange(
              walls,
              Math.max(rightRangeStart[index - 1], leftRangeStart[index]),
              Math.min(rightRangeEnd[index - 1], leftRangeEnd[index]));

      long currentBestIfShootLeft =
          Math.max(
              bestIfShootLeft + leftDestroyedCount[index],
              bestIfShootRight + leftDestroyedCount[index] - overlapWalls);

      long currentBestIfShootRight =
          Math.max(bestIfShootLeft, bestIfShootRight) + rightDestroyedCount[index];

      bestIfShootLeft = currentBestIfShootLeft;
      bestIfShootRight = currentBestIfShootRight;
    }

    return (int) Math.max(bestIfShootLeft, bestIfShootRight);
  }

  private int countWallsInRange(int[] walls, long leftInclusive, long rightInclusive) {
    if (leftInclusive > rightInclusive) {
      return 0;
    }

    int leftIndex = lowerBound(walls, leftInclusive);
    int rightIndex = upperBound(walls, rightInclusive);

    return rightIndex - leftIndex;
  }

  private int lowerBound(int[] array, long target) {
    int left = 0;
    int right = array.length;

    while (left < right) {
      int middle = left + (right - left) / 2;
      if (array[middle] >= target) {
        right = middle;
      } else {
        left = middle + 1;
      }
    }

    return left;
  }

  private int upperBound(int[] array, long target) {
    int left = 0;
    int right = array.length;

    while (left < right) {
      int middle = left + (right - left) / 2;
      if (array[middle] > target) {
        right = middle;
      } else {
        left = middle + 1;
      }
    }

    return left;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.maxWalls(new int[] {4}, new int[] {3}, new int[] {1, 10})); // 1

    System.out.println(
        solution.maxWalls(new int[] {10, 2}, new int[] {5, 1}, new int[] {5, 2, 7})); // 3

    System.out.println(
        solution.maxWalls(new int[] {1, 2}, new int[] {100, 1}, new int[] {10})); // 0

    System.out.println(
        solution.maxWalls(new int[] {5, 15}, new int[] {10, 10}, new int[] {1, 3, 5, 10, 15, 20}));

    System.out.println(
        solution.maxWalls(
            new int[] {8, 20, 30}, new int[] {5, 10, 5}, new int[] {3, 8, 12, 18, 20, 25, 30, 35}));
  }
}
