package dev.vkh.solutions;

class Solution {

  public static int sortPermutation(int[] nums) {
    int result = -1;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != i) {
        if (result == -1) {
          result = nums[i];
        } else {
          result &= nums[i];
        }
      }
    }

    return result == -1 ? 0 : result;
  }

  static void main() {
    System.out.println(sortPermutation(new int[] {0, 3, 2, 1})); // 1
    System.out.println(sortPermutation(new int[] {0, 1, 3, 2})); // 2
    System.out.println(sortPermutation(new int[] {3, 2, 1, 0})); // 0
    System.out.println(sortPermutation(new int[] {0, 1, 2, 3})); // 0
    System.out.println(sortPermutation(new int[] {0, 1, 4, 3, 2})); // 0
  }
}
