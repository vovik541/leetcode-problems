package dev.vkh.solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
  static void main() {
    //    System.out.println(new Solution().minimumAbsDifference(new int[] {4, 2, 1, 3}));
    System.out.println(new Solution().minimumAbsDifference(new int[] {1, 3, 6, 10, 15}));
  }

  public List<List<Integer>> minimumAbsDifference(int[] arr) {
    Arrays.sort(arr);

    List<List<Integer>> minimumPairs = new LinkedList<>();
    int minDifference = Integer.MAX_VALUE;
    int currentDifference;

    for (int i = 0; i < arr.length - 1; i++) {
      currentDifference = Math.abs(arr[i + 1] - arr[i]);
      if (minDifference > currentDifference) {
        minimumPairs.clear();
        minDifference = currentDifference;
      }
      if (minDifference == currentDifference) {
        minimumPairs.add(List.of(arr[i], arr[i + 1]));
      }
    }

    return minimumPairs;
  }
}
