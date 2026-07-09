package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {

  public static boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
    int[] componentId = new int[n];

    int currentComponent = 0;
    componentId[0] = currentComponent;

    for (int index = 1; index < n; index++) {
      if (nums[index] - nums[index - 1] > maxDiff) {
        currentComponent++;
      }

      componentId[index] = currentComponent;
    }

    boolean[] answer = new boolean[queries.length];

    for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
      int firstNode = queries[queryIndex][0];
      int secondNode = queries[queryIndex][1];

      answer[queryIndex] = componentId[firstNode] == componentId[secondNode];
    }

    return answer;
  }

  static void main() {
    int n = 4;
    int[] nums = {2, 5, 6, 8};
    int maxDiff = 2;
    int[][] queries2 = {
      {0, 1},
      {0, 2},
      {1, 3},
      {2, 3}
    };
    System.out.println(
        Arrays.toString(
            pathExistenceQueries(n, nums, maxDiff, queries2))); // [false, false, true, true]
  }
}
