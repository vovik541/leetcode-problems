package dev.vkh.solutions;

class Solution {

  public int findMin(int[] nums) {
    int leftIndex = 0;
    int rightIndex = nums.length - 1;

    while (leftIndex < rightIndex) {
      int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

      if (nums[middleIndex] > nums[rightIndex]) {
        leftIndex = middleIndex + 1;
      } else {
        rightIndex = middleIndex;
      }
    }

    return nums[leftIndex];
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.findMin(new int[] {3, 4, 5, 1, 2})); // 1
    System.out.println(solution.findMin(new int[] {4, 5, 6, 7, 0, 1, 2})); // 0
  }
}
