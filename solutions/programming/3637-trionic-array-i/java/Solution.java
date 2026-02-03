package dev.vkh.solutions;

public class Solution {

  public boolean isTrionic(int[] nums) {
    byte directionChanges = 1;

    if (nums[1] - nums[0] <= 0) return false;

    for (int i = 2; i < nums.length; i++) {
      if (directionChanges > 3) return false;

      if (nums[i] - nums[i - 1] == 0) return false;

      if (directionChanges % 2 == 1) {
        if (nums[i] - nums[i - 1] < 0) directionChanges++;
      } else {
        if (nums[i] - nums[i - 1] > 0) directionChanges++;
      }
    }

    return directionChanges == 3;
  }

  static void main() {
    System.out.println(new Solution().isTrionic(new int[] {8, 8, 5, 4, 2, 6}));
  }
}
