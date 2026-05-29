package dev.vkh.solutions;

class Solution {

  public int minElement(int[] nums) {
    int minDigitsSum = Integer.MAX_VALUE;
    int currentDigitsSum;
    for (int num : nums) {
      currentDigitsSum = 0;
      while (num > 0) {
        currentDigitsSum += num % 10;
        num /= 10;
      }
      if (currentDigitsSum < minDigitsSum) {
        minDigitsSum = currentDigitsSum;
      }
    }

    return minDigitsSum;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.minElement(new int[] {10, 12, 13, 14})); // 1
  }
}
