package dev.vkh.solutions;

class Solution {

  public int minMoves(int[] nums, int limit) {
    int[] moveChanges = new int[2 * limit + 2];

    for (int leftIndex = 0, rightIndex = nums.length - 1;
        leftIndex < rightIndex;
        leftIndex++, rightIndex--) {

      int smallerValue = Math.min(nums[leftIndex], nums[rightIndex]);
      int largerValue = Math.max(nums[leftIndex], nums[rightIndex]);
      int currentPairSum = smallerValue + largerValue;

      moveChanges[2] += 2;

      moveChanges[smallerValue + 1] -= 1;
      moveChanges[currentPairSum] -= 1;

      moveChanges[currentPairSum + 1] += 1;
      moveChanges[largerValue + limit + 1] += 1;
    }

    int minimumMoves = Integer.MAX_VALUE;
    int currentMoves = 0;

    for (int targetSum = 2; targetSum <= 2 * limit; targetSum++) {
      currentMoves += moveChanges[targetSum];
      minimumMoves = Math.min(minimumMoves, currentMoves);
    }

    return minimumMoves;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.minMoves(new int[] {1, 2, 4, 3}, 4)); // 1
    System.out.println(solution.minMoves(new int[] {1, 2, 2, 1}, 2)); // 2
    System.out.println(solution.minMoves(new int[] {1, 2, 1, 2}, 2)); // 0
  }
}
