package dev.vkh.solutions;

public class Solution {
  static void main(String[] args) {
    Solution solution = new Solution();
    int[][] bottomLeft1 = {{1, 1}, {2, 2}, {3, 1}};
    int[][] topRight1 = {{3, 3}, {4, 4}, {6, 6}};
    System.out.println(solution.largestSquareArea(bottomLeft1, topRight1));

    //    int[][] bottomLeft2 = {{1, 1}, {1, 3}, {1, 5}};
    //    int[][] topRight2 = {{5, 5}, {5, 7}, {5, 9}};
    //    System.out.println(solution.largestSquareArea(bottomLeft2, topRight2));
    //
    //    int[][] bottomLeft4 = {{1, 1}, {3, 3}, {3, 1}};
    //    int[][] topRight4 = {{2, 2}, {4, 4}, {4, 2}};
    //    System.out.println(solution.largestSquareArea(bottomLeft4, topRight4));
  }

  public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
    int rectangleCount = bottomLeft.length;
    long bestArea = 0L;

    for (int firstIndex = 0; firstIndex < rectangleCount; firstIndex++) {
      int firstLeftX = bottomLeft[firstIndex][0];
      int firstBottomY = bottomLeft[firstIndex][1];
      int firstRightX = topRight[firstIndex][0];
      int firstTopY = topRight[firstIndex][1];

      for (int secondIndex = firstIndex + 1; secondIndex < rectangleCount; secondIndex++) {
        int secondLeftX = bottomLeft[secondIndex][0];
        int secondBottomY = bottomLeft[secondIndex][1];
        int secondRightX = topRight[secondIndex][0];
        int secondTopY = topRight[secondIndex][1];

        int intersectionLeftX = Math.max(firstLeftX, secondLeftX);
        int intersectionBottomY = Math.max(firstBottomY, secondBottomY);
        int intersectionRightX = Math.min(firstRightX, secondRightX);
        int intersectionTopY = Math.min(firstTopY, secondTopY);

        if (intersectionLeftX >= intersectionRightX || intersectionBottomY >= intersectionTopY) {
          continue;
        }

        long intersectionWidth = (long) intersectionRightX - intersectionLeftX;
        long intersectionHeight = (long) intersectionTopY - intersectionBottomY;
        long maxSquareSide = Math.min(intersectionWidth, intersectionHeight);

        long area = maxSquareSide * maxSquareSide;
        if (area > bestArea) {
          bestArea = area;
        }
      }
    }

    return bestArea;
  }
}
