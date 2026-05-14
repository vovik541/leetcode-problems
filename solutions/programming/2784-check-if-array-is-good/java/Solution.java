package dev.vkh.solutions;

class Solution {

  public boolean isGood(int[] nums) {
    int arrayLength = nums.length;
    int expectedMaximumValue = arrayLength - 1;

    int[] frequencyByValue = new int[arrayLength];

    for (int value : nums) {
      if (value < 1 || value > expectedMaximumValue) {
        return false;
      }

      frequencyByValue[value - 1]++;
    }

    for (int value = 1; value < expectedMaximumValue; value++) {
      if (frequencyByValue[value - 1] != 1) {
        return false;
      }
    }

    return frequencyByValue[expectedMaximumValue - 1] == 2;
  }

  static void main() {
    System.out.println(new Solution().isGood(new int[] {3, 4, 4, 1, 2, 1})); // false
  }
}
