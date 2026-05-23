package dev.vkh.solutions;

class Solution {

  public boolean check(int[] nums) {
    int decreaseCount = 0;

    for (int index = 0; index < nums.length; index++) {
      int nextIndex = (index + 1) % nums.length;

      if (nums[index] > nums[nextIndex]) {
        decreaseCount++;
      }
    }

    return decreaseCount <= 1;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.check(new int[] {3, 4, 5, 1, 2})); // true
  }
}
