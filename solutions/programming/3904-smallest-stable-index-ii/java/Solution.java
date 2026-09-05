package dev.vkh.solutions;

class Solution {
  public static int firstStableIndex(int[] nums, int k) {
    int n = nums.length;
    int[] suf = new int[n];
    suf[n - 1] = nums[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      suf[i] = Math.min(nums[i], suf[i + 1]);
    }

    int pre = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      pre = Math.max(pre, nums[i]);
      if (pre - suf[i] <= k) return i;
    }
    return -1;
  }

  static void main() {
    System.out.println(firstStableIndex(new int[] {5, 0, 1, 4}, 3)); // 3
    System.out.println(firstStableIndex(new int[] {3, 2, 1}, 1)); // -1
    System.out.println(firstStableIndex(new int[] {0}, 0)); // 0
  }
}
