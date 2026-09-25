package dev.vkh.solutions;

import java.util.*;

class Solution {
  public int smallestIndex(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      if (digitSum(nums[i]) == i) {
        return i;
      }
    }

    return -1;
  }

  private int digitSum(int number) {
    int sum = 0;

    while (number > 0) {
      sum += number % 10;
      number /= 10;
    }

    return sum;
  }
}
