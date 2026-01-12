package dev.vkh.solutions;

public class Solution {
  static void main(String[] args) {
//    System.out.println(new Solution().minTimeToVisitAllPoints(new int[][] {{1, 1}, {3, 4}, {-1, 0}})); // 7
//    System.out.println(new Solution().minTimeToVisitAllPoints(new int[][] {{3, 2}, {-2, 2}})); // 5
    System.out.println(new Solution().minTimeToVisitAllPoints(new int[][] {{0, 0}, {0, 0}})); // 0
  }

  public int minTimeToVisitAllPoints(int[][] points)  {
    int totalSeconds = 0;

    for (int pointIndex = 1; pointIndex < points.length; pointIndex++) {
      int previousX = points[pointIndex - 1][0];
      int previousY = points[pointIndex - 1][1];

      int currentX = points[pointIndex][0];
      int currentY = points[pointIndex][1];

      int deltaX = Math.abs(currentX - previousX);
      int deltaY = Math.abs(currentY - previousY);

      totalSeconds += Math.max(deltaX, deltaY);
    }

    return totalSeconds;
  }
}
