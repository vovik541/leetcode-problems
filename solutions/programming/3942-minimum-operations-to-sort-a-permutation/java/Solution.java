package dev.vkh.solutions;

class Solution {

  public static int minOperations(int[] nums) {
    int length = nums.length;

    if (length == 1) {
      return 0;
    }

    boolean increasing = true;
    boolean decreasing = true;
    int zeroIndex = -1;

    for (int i = 0; i < length; i++) {
      int next = nums[(i + 1) % length];

      if (next != (nums[i] + 1) % length) {
        increasing = false;
      }

      if (next != (nums[i] - 1 + length) % length) {
        decreasing = false;
      }

      if (nums[i] == 0) {
        zeroIndex = i;
      }
    }

    int result = Integer.MAX_VALUE;

    if (increasing) {
      // Rotate left until zero becomes the first element.
      result = Math.min(result, zeroIndex);

      // Reverse, rotate left, then reverse again.
      result = Math.min(result, length - zeroIndex + 2);
    }

    if (decreasing) {
      // Reverse first, then rotate left.
      int rotationsAfterReverse = length - 1 - zeroIndex;
      result = Math.min(result, 1 + rotationsAfterReverse);

      // Rotate left first, then reverse.
      int rotationsBeforeReverse = (zeroIndex + 1) % length;
      result = Math.min(result, rotationsBeforeReverse + 1);
    }

    return result == Integer.MAX_VALUE ? -1 : result;
  }

  static void main() {
    System.out.println(minOperations(new int[] {0, 2, 1})); // 2
    System.out.println(minOperations(new int[] {1, 0, 2})); // 2
    System.out.println(minOperations(new int[] {2, 0, 1, 3})); // -1
    System.out.println(minOperations(new int[] {0, 1, 2, 3})); // 0
    System.out.println(minOperations(new int[] {1, 2, 3, 0})); // 3
    System.out.println(minOperations(new int[] {3, 2, 1, 0})); // 1
    System.out.println(minOperations(new int[] {2, 1, 0})); // 1
    System.out.println(minOperations(new int[] {1, 0})); // 1
  }
}
