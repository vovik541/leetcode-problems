package dev.vkh.solutions;

class Solution {

  public int search(int[] nums, int target) {
    int leftIndex = 0;
    int rightIndex = nums.length - 1;

    while (leftIndex <= rightIndex) {
      int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

      if (nums[middleIndex] == target) {
        return middleIndex;
      }

      if (nums[leftIndex] <= nums[middleIndex]) {
        if (target >= nums[leftIndex] && target < nums[middleIndex]) {
          rightIndex = middleIndex - 1;
        } else {
          leftIndex = middleIndex + 1;
        }
      } else {
        if (target > nums[middleIndex] && target <= nums[rightIndex]) {
          leftIndex = middleIndex + 1;
        } else {
          rightIndex = middleIndex - 1;
        }
      }
    }

    return -1;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0)); // 4
    System.out.println(solution.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3)); // -1
  }
}
