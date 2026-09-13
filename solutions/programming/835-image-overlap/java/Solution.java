package dev.vkh.solutions;

import java.util.HashMap;
import java.util.Map;

class Solution {

  public static int largestOverlap(int[][] img1, int[][] img2) {
    Map<Integer, Integer> translationCount = new HashMap<>();
    int maximumOverlap = 0;

    for (int row1 = 0; row1 < img1.length; row1++) {
      for (int column1 = 0; column1 < img1.length; column1++) {
        if (img1[row1][column1] == 0) {
          continue;
        }

        for (int row2 = 0; row2 < img2.length; row2++) {
          for (int column2 = 0; column2 < img2.length; column2++) {
            if (img2[row2][column2] == 0) {
              continue;
            }

            int rowShift = row2 - row1;
            int columnShift = column2 - column1;

            int shiftKey = (rowShift + 30) * 61 + (columnShift + 30);

            int currentOverlap = translationCount.merge(shiftKey, 1, Integer::sum);

            maximumOverlap = Math.max(maximumOverlap, currentOverlap);
          }
        }
      }
    }

    return maximumOverlap;
  }

  static void main() {
    System.out.println(
        largestOverlap(
            new int[][] {
              {1, 1, 0},
              {0, 1, 0},
              {0, 1, 0}
            },
            new int[][] {
              {0, 0, 0},
              {0, 1, 1},
              {0, 0, 1}
            })); // 3

    System.out.println(largestOverlap(new int[][] {{1}}, new int[][] {{1}})); // 1

    System.out.println(largestOverlap(new int[][] {{0}}, new int[][] {{0}})); // 0
  }
}
