package dev.vkh.solutions;

class Solution {
  public int getMinDistance(int[] nums, int target, int start) {
    int minAbsolute = Integer.MAX_VALUE;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == target) {
        minAbsolute = Math.min(minAbsolute, Math.abs(i - start));
      }
    }

    return minAbsolute;
  }

  static void main() {
    System.out.println(new Solution().getMinDistance(new int[] {1, 2, 3, 4, 5}, 5, 3));
  }
}
