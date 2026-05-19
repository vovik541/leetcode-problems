package dev.vkh.solutions;

class Solution {

  public int getCommon(int[] nums1, int[] nums2) {
    int firstArrayIndex = 0;
    int secondArrayIndex = 0;

    while (firstArrayIndex < nums1.length && secondArrayIndex < nums2.length) {
      if (nums1[firstArrayIndex] == nums2[secondArrayIndex]) {
        return nums1[firstArrayIndex];
      }

      if (nums1[firstArrayIndex] < nums2[secondArrayIndex]) {
        firstArrayIndex++;
      } else {
        secondArrayIndex++;
      }
    }

    return -1;
  }

  //  public int getCommon(int[] nums1, int[] nums2) {
  //    main:
  //    for (int num : nums1) {
  //      for (int num2 : nums2) {
  //        if (num > num2) {
  //          continue main;
  //        }
  //
  //        if (num == num2) {
  //          return num;
  //        }
  //      }
  //    }
  //
  //    return -1;
  //  }

  static void main() {
    //    System.out.println(
    //        new Solution().getCommon(new int[] {1, 2, 3, 6}, new int[] {2, 3, 4, 5})); // 2
    System.out.println(new Solution().getCommon(new int[] {2, 4}, new int[] {1, 2})); // 2
  }
}
