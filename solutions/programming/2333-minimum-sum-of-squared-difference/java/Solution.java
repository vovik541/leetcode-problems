package dev.vkh.solutions;

class Solution {

  public static long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {

    int[] differences = new int[nums1.length];
    int maximumDifference = 0;
    long totalDifference = 0;
    long operations = (long) k1 + k2;

    for (int i = 0; i < nums1.length; i++) {
      int difference = Math.abs(nums1[i] - nums2[i]);

      differences[i] = difference;
      maximumDifference = Math.max(maximumDifference, difference);
      totalDifference += difference;
    }

    if (totalDifference <= operations) {
      return 0;
    }

    int left = 0;
    int right = maximumDifference;

    while (left < right) {
      int middle = left + (right - left) / 2;
      long requiredOperations = 0;

      for (int difference : differences) {
        if (difference > middle) {
          requiredOperations += difference - middle;
        }
      }

      if (requiredOperations <= operations) {
        right = middle;
      } else {
        left = middle + 1;
      }
    }

    int targetDifference = left;

    for (int difference : differences) {
      if (difference > targetDifference) {
        operations -= difference - targetDifference;
      }
    }

    long result = 0;

    for (int difference : differences) {
      int reducedDifference = Math.min(difference, targetDifference);

      if (reducedDifference == targetDifference && operations > 0) {
        reducedDifference--;
        operations--;
      }

      result += (long) reducedDifference * reducedDifference;
    }

    return result;
  }

  static void main() {
    System.out.println(
        minSumSquareDiff(new int[] {1, 2, 3, 4}, new int[] {2, 10, 20, 19}, 0, 0)); // 579
    System.out.println(
        minSumSquareDiff(new int[] {1, 4, 10, 12}, new int[] {5, 8, 6, 9}, 1, 1)); // 43
    System.out.println(minSumSquareDiff(new int[] {1, 2}, new int[] {1, 2}, 100, 100)); // 0
  }
}
