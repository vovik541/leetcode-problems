package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public static int minSumOfLengths(int[] arr, int target) {
    int length = arr.length;
    int[] minimumLengthUntil = new int[length];

    Arrays.fill(minimumLengthUntil, Integer.MAX_VALUE);

    int leftIndex = 0;
    int currentSum = 0;
    int bestLength = Integer.MAX_VALUE;
    int minimumTotalLength = Integer.MAX_VALUE;

    for (int rightIndex = 0; rightIndex < length; rightIndex++) {
      currentSum += arr[rightIndex];

      while (currentSum > target && leftIndex <= rightIndex) {
        currentSum -= arr[leftIndex++];
      }

      if (currentSum == target) {
        int currentLength = rightIndex - leftIndex + 1;

        if (leftIndex > 0 && minimumLengthUntil[leftIndex - 1] != Integer.MAX_VALUE) {
          minimumTotalLength =
              Math.min(minimumTotalLength, currentLength + minimumLengthUntil[leftIndex - 1]);
        }

        bestLength = Math.min(bestLength, currentLength);
      }

      minimumLengthUntil[rightIndex] = bestLength;
    }

    return minimumTotalLength == Integer.MAX_VALUE ? -1 : minimumTotalLength;
  }

  static void main() {
    System.out.println(minSumOfLengths(new int[] {3, 2, 2, 4, 3}, 3)); // 2
    System.out.println(minSumOfLengths(new int[] {7, 3, 4, 7}, 7)); // 2
    System.out.println(minSumOfLengths(new int[] {4, 3, 2, 6, 2, 3, 4}, 6)); // -1
  }
}
