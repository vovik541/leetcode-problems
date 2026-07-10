package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {

  public static int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
    int[][] nodes = new int[n][2];

    for (int index = 0; index < n; index++) {
      nodes[index][0] = nums[index];
      nodes[index][1] = index;
    }

    Arrays.sort(nodes, (first, second) -> Integer.compare(first[0], second[0]));

    int[] sortedValues = new int[n];
    int[] originalIndexToSortedIndex = new int[n];

    for (int sortedIndex = 0; sortedIndex < n; sortedIndex++) {
      sortedValues[sortedIndex] = nodes[sortedIndex][0];
      int originalIndex = nodes[sortedIndex][1];
      originalIndexToSortedIndex[originalIndex] = sortedIndex;
    }

    int[] farthestReachable = buildFarthestReachable(sortedValues, maxDiff);
    int[] componentId = buildComponentIds(sortedValues, maxDiff);

    int log = 1;

    while ((1 << log) <= n) {
      log++;
    }

    int[][] jump = new int[log][n];
    jump[0] = farthestReachable;

    for (int level = 1; level < log; level++) {
      for (int index = 0; index < n; index++) {
        jump[level][index] = jump[level - 1][jump[level - 1][index]];
      }
    }

    int[] answer = new int[queries.length];

    for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
      int firstNode = queries[queryIndex][0];
      int secondNode = queries[queryIndex][1];

      int left = originalIndexToSortedIndex[firstNode];
      int right = originalIndexToSortedIndex[secondNode];

      if (left == right) {
        answer[queryIndex] = 0;
        continue;
      }

      if (left > right) {
        int temp = left;
        left = right;
        right = temp;
      }

      if (componentId[left] != componentId[right]) {
        answer[queryIndex] = -1;
        continue;
      }

      answer[queryIndex] = getMinimumDistance(left, right, jump);
    }

    return answer;
  }

  private static int[] buildFarthestReachable(int[] sortedValues, int maxDiff) {
    int n = sortedValues.length;
    int[] farthestReachable = new int[n];

    int right = 0;

    for (int left = 0; left < n; left++) {
      while (right + 1 < n && sortedValues[right + 1] - sortedValues[left] <= maxDiff) {
        right++;
      }

      farthestReachable[left] = right;
    }

    return farthestReachable;
  }

  private static int[] buildComponentIds(int[] sortedValues, int maxDiff) {
    int n = sortedValues.length;
    int[] componentId = new int[n];

    int currentComponent = 0;
    componentId[0] = currentComponent;

    for (int index = 1; index < n; index++) {
      if (sortedValues[index] - sortedValues[index - 1] > maxDiff) {
        currentComponent++;
      }

      componentId[index] = currentComponent;
    }

    return componentId;
  }

  private static int getMinimumDistance(int left, int right, int[][] jump) {
    int currentIndex = left;
    int distance = 0;

    for (int level = jump.length - 1; level >= 0; level--) {
      if (jump[level][currentIndex] < right) {
        currentIndex = jump[level][currentIndex];
        distance += 1 << level;
      }
    }

    return distance + 1;
  }

  static void main() {
    int n1 = 5;
    int[] nums1 = {1, 8, 3, 4, 2};
    int maxDiff1 = 3;
    int[][] queries1 = {
      {0, 3},
      {2, 4}
    };

    System.out.println(
        Arrays.toString(pathExistenceQueries(n1, nums1, maxDiff1, queries1))); // [1, 1]

    int n2 = 5;
    int[] nums2 = {5, 3, 1, 9, 10};
    int maxDiff2 = 2;
    int[][] queries2 = {
      {0, 1},
      {0, 2},
      {2, 3},
      {4, 3}
    };

    System.out.println(
        Arrays.toString(pathExistenceQueries(n2, nums2, maxDiff2, queries2))); // [1, 2, -1, 1]
  }
}
