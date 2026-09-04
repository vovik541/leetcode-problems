package dev.vkh.solutions;

class Solution {

  public static int firstStableIndex(int[] nums, int k) {

    int potentialStable;

    for (int i = 0; i < nums.length; i++) {
      potentialStable = getMax(0, i, nums) - getMin(i, nums.length - 1, nums);
      if (potentialStable <= k) {
        return i;
      }
    }

    return -1;
  }

  private static int getMax(int from, int to, int[] nums) {
    int max = Integer.MIN_VALUE;
    for (int i = from; i <= to; i++) {
      max = Math.max(max, nums[i]);
    }

    return max;
  }

  private static int getMin(int from, int to, int[] nums) {
    int min = Integer.MAX_VALUE;
    for (int i = from; i <= to; i++) {
      min = Math.min(min, nums[i]);
    }

    return min;
  }

  static void main() {
    System.out.println(firstStableIndex(new int[] {5, 0, 1, 4}, 3)); // 3
    System.out.println(firstStableIndex(new int[] {3, 2, 1}, 1)); // -1
    System.out.println(firstStableIndex(new int[] {0}, 0)); // 0
  }
}
