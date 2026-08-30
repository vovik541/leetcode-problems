package dev.vkh.solutions;

class Solution {

  public static int minimumDeletions(int[] nums) {
    int minIndex = 0;
    int maxIndex = 0;

    for (int index = 1; index < nums.length; index++) {
      if (nums[index] < nums[minIndex]) {
        minIndex = index;
      }

      if (nums[index] > nums[maxIndex]) {
        maxIndex = index;
      }
    }

    int leftIndex = Math.min(minIndex, maxIndex);
    int rightIndex = Math.max(minIndex, maxIndex);

    int deleteFromFront = rightIndex + 1;
    int deleteFromBack = nums.length - leftIndex;
    int deleteFromBothSides = (leftIndex + 1) + (nums.length - rightIndex);

    return Math.min(deleteFromFront, Math.min(deleteFromBack, deleteFromBothSides));
  }

  static void main() {
    System.out.println(minimumDeletions(new int[] {2, 10, 7, 5, 4, 1, 8, 6})); // 5
    System.out.println(minimumDeletions(new int[] {0, -4, 19, 1, 8, -2, -3, 5})); // 3
    System.out.println(minimumDeletions(new int[] {101})); // 1
  }
}
