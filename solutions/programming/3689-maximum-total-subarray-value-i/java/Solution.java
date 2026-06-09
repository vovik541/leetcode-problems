package dev.vkh.solutions;

class Solution {

  public long maxTotalValue(int[] nums, int k) {
    int minimumValue = Integer.MAX_VALUE;
    int maximumValue = Integer.MIN_VALUE;

    for (int value : nums) {
      minimumValue = Math.min(minimumValue, value);
      maximumValue = Math.max(maximumValue, value);
    }

    return (long) (maximumValue - minimumValue) * k;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.maxTotalValue(new int[] {1, 3, 2}, 2)); // 4
  }
}
