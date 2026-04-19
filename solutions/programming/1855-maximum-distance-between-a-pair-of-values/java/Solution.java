package dev.vkh.solutions;

class Solution {
  public int maxDistance(int[] nums1, int[] nums2) {
    int maximumDistance = 0;

    for (int firstArrayIndex = 0; firstArrayIndex < nums1.length; firstArrayIndex++) {
      maximumDistance =
          Math.max(
              maximumDistance,
              findMaximumDistanceForIndex(firstArrayIndex, nums1[firstArrayIndex], nums2));
    }

    return maximumDistance;
  }

  private int findMaximumDistanceForIndex(int firstArrayIndex, int firstArrayValue, int[] nums2) {
    int leftPointer = firstArrayIndex;
    int rightPointer = nums2.length - 1;
    int bestIndex = -1;

    while (leftPointer <= rightPointer) {
      int middleIndex = leftPointer + (rightPointer - leftPointer) / 2;

      if (nums2[middleIndex] >= firstArrayValue) {
        bestIndex = middleIndex;
        leftPointer = middleIndex + 1;
      } else {
        rightPointer = middleIndex - 1;
      }
    }

    return bestIndex == -1 ? 0 : bestIndex - firstArrayIndex;
  }

  static void main() {
    System.out.println(
        new Solution().maxDistance(new int[] {55, 30, 5, 4, 2}, new int[] {100, 20, 10, 10, 5}));
  }
}
