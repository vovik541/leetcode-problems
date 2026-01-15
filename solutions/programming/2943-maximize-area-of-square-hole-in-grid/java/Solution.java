package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {
  public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
    int maxConsecutiveHorizontalBars = longestConsecutiveRunLength(hBars);
    int maxConsecutiveVerticalBars = longestConsecutiveRunLength(vBars);

    int squareSideLength = Math.min(maxConsecutiveHorizontalBars, maxConsecutiveVerticalBars) + 1;
    return squareSideLength * squareSideLength;
  }

  private int longestConsecutiveRunLength(int[] bars) {
    Arrays.sort(bars);

    int longestRun = 1;
    int currentRun = 1;

    for (int index = 1; index < bars.length; index++) {
      if (bars[index] == bars[index - 1] + 1) {
        currentRun++;
      } else {
        currentRun = 1;
      }
      longestRun = Math.max(longestRun, currentRun);
    }

    return longestRun;
  }

  static void main() {
    int n1 = 2, m1 = 1;
    int[] hBars1 = {2, 3};
    int[] vBars1 = {2};
    System.out.println(new Solution().maximizeSquareHoleArea(n1, m1, hBars1, vBars1));
  }
}
