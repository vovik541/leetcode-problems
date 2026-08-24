package dev.vkh.solutions;

class Solution {

  public static int stoneGameVIII(int[] stones) {
    int stoneCount = stones.length;

    long[] prefixSums = new long[stoneCount];
    prefixSums[0] = stones[0];

    for (int index = 1; index < stoneCount; index++) {
      prefixSums[index] = prefixSums[index - 1] + stones[index];
    }

    long maximumScoreDifference = prefixSums[stoneCount - 1];

    for (int index = stoneCount - 2; index >= 1; index--) {
      maximumScoreDifference =
          Math.max(maximumScoreDifference, prefixSums[index] - maximumScoreDifference);
    }

    return (int) maximumScoreDifference;
  }

  static void main() {
    System.out.println(stoneGameVIII(new int[] {-1, 2, -3, 4, -5})); // 5
    System.out.println(stoneGameVIII(new int[] {7, -6, 5, 10, 5, -2, -6})); // 13
    System.out.println(stoneGameVIII(new int[] {-10, -12})); // -22
  }
}
