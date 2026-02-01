package dev.vkh.solutions;

import java.util.*;

public class Solution {
  public int minimumCost(int[] nums) {
    int smallestValue = Integer.MAX_VALUE;
    int secondSmallestValue = Integer.MAX_VALUE;

    for (int index = 1; index < nums.length; index++) {
      int currentValue = nums[index];
      if (currentValue < smallestValue) {
        secondSmallestValue = smallestValue;
        smallestValue = currentValue;
      } else if (currentValue < secondSmallestValue) {
        secondSmallestValue = currentValue;
      }
    }

    return nums[0] + smallestValue + secondSmallestValue;
  }

  static void main() {
    Solution solution = new Solution();
    System.out.println(solution.minimumCost(new int[] {1, 2, 3, 12}));
    //    System.out.println(solution.minimumCost(new int[]{5, 4, 3}));
    //    System.out.println(solution.minimumCost(new int[]{10, 3, 1, 1}));
  }
}
