package dev.vkh.solutions;

class Solution {

  public int findMin(int[] nums) {
    int leftIndex = 0;
    int rightIndex = nums.length - 1;

    while (leftIndex < rightIndex) {
      int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

      if (nums[middleIndex] > nums[rightIndex]) {
        leftIndex = middleIndex + 1;
      } else if (nums[middleIndex] < nums[rightIndex]) {
        rightIndex = middleIndex;
      } else {
        rightIndex--;
      }
    }

    return nums[leftIndex];
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.findMin(new int[] {2, 2, 2, 0, 1})); // 0
    System.out.println(solution.findMin(new int[] {1, 1, 1, 1, 1})); // 1
  }
}
