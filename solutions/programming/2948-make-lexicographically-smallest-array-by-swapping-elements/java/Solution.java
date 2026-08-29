package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public static int[] lexicographicallySmallestArray(int[] nums, int limit) {
    int[][] valueWithIndex = new int[nums.length][2];

    for (int index = 0; index < nums.length; index++) {
      valueWithIndex[index][0] = nums[index];
      valueWithIndex[index][1] = index;
    }

    Arrays.sort(valueWithIndex, (first, second) -> Integer.compare(first[0], second[0]));

    int groupStart = 0;

    while (groupStart < nums.length) {
      int groupEnd = groupStart;

      while (groupEnd + 1 < nums.length
          && (long) valueWithIndex[groupEnd + 1][0] - valueWithIndex[groupEnd][0] <= limit) {
        groupEnd++;
      }

      int groupSize = groupEnd - groupStart + 1;
      int[] indices = new int[groupSize];

      for (int offset = 0; offset < groupSize; offset++) {
        indices[offset] = valueWithIndex[groupStart + offset][1];
      }

      Arrays.sort(indices);

      for (int offset = 0; offset < groupSize; offset++) {
        nums[indices[offset]] = valueWithIndex[groupStart + offset][0];
      }

      groupStart = groupEnd + 1;
    }

    return nums;
  }

  static void main() {
    System.out.println(
        Arrays.toString(lexicographicallySmallestArray(new int[] {1, 5, 3, 9, 8}, 2)));
    // [1, 3, 5, 8, 9]

    System.out.println(
        Arrays.toString(lexicographicallySmallestArray(new int[] {1, 7, 6, 18, 2, 1}, 3)));
    // [1, 6, 7, 18, 1, 2]

    System.out.println(
        Arrays.toString(lexicographicallySmallestArray(new int[] {1, 7, 28, 19, 10}, 3)));
    // [1, 7, 28, 19, 10]
  }
}
