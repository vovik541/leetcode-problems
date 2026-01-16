package dev.vkh.solutions;

import java.util.*;

class Solution {
  private static final long MOD = 1_000_000_007L;

  public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
    int[] horizontalLines = buildSortedLines(m, hFences);
    int[] verticalLines = buildSortedLines(n, vFences);

    HashSet<Integer> horizontalDistances = new HashSet<>();
    for (int i = 0; i < horizontalLines.length; i++) {
      for (int j = i + 1; j < horizontalLines.length; j++) {
        horizontalDistances.add(horizontalLines[j] - horizontalLines[i]);
      }
    }

    int bestSideLength = 0;

    for (int i = 0; i < verticalLines.length; i++) {
      for (int j = i + 1; j < verticalLines.length; j++) {
        int width = verticalLines[j] - verticalLines[i];
        if (width > bestSideLength && horizontalDistances.contains(width)) {
          bestSideLength = width;
        }
      }
    }

    if (bestSideLength == 0) {
      return -1;
    }

    long area = (long) bestSideLength * (long) bestSideLength;
    return (int) (area % MOD);
  }

  private int[] buildSortedLines(int limit, int[] fences) {
    int[] lines = new int[fences.length + 2];
    lines[0] = 1;
    lines[1] = limit;
    for (int i = 0; i < fences.length; i++) {
      lines[i + 2] = fences[i];
    }
    Arrays.sort(lines);
    return lines;
  }

  static void main() {
    Solution solution = new Solution();

    int m1 = 4, n1 = 3;
    int[] h1 = {2, 3};
    int[] v1 = {2};
    System.out.println(solution.maximizeSquareArea(m1, n1, h1, v1));
  }
}
